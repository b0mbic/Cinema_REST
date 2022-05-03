package cinema.service;

import cinema.domain.CinemaRoom;
import cinema.domain.Seat;
import cinema.domain.Ticket;
import cinema.exception.ApiException;
import cinema.model.ReturnTicketRequest;
import cinema.model.ReturnedTicketDto;
import cinema.model.SeatRequestDto;
import cinema.model.StatsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CinemaService {

    private final CinemaRoom cinemaRoom = new CinemaRoom(9, 9);
    private final ConcurrentHashMap<UUID, Seat> tickets = cinemaRoom.getTickets();



    public ResponseEntity<CinemaRoom> getSeats() {
        return new ResponseEntity<>(cinemaRoom, HttpStatus.OK);
    }


    public ResponseEntity<Ticket> buyTicket(SeatRequestDto seatRequest) {
        Seat foundSeat = null;
        for (Seat seat : cinemaRoom.getAvailableSeats()) {
            if (seat.getRow() == seatRequest.getRow() && seat.getColumn() == seatRequest.getColumn()) {
                foundSeat = seat;
            }
        }

        if (foundSeat == null) {
            throw new ApiException("The number of a row or a column is out of bounds!");
        }

        if (!foundSeat.isAvailable()) {
            throw new ApiException("The ticket has been already purchased!");
        }

        foundSeat.setAvailable(false);
        Ticket ticket = new Ticket(UUID.randomUUID(), foundSeat);
        tickets.put(ticket.getToken(), ticket.getTicket());
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }


    public ResponseEntity<ReturnedTicketDto> returnTicket(ReturnTicketRequest returnRequest) {
        if (!tickets.containsKey(returnRequest.getToken())) {
            throw new ApiException("Wrong token!");
        }

        Seat returnedSeat = tickets.remove(returnRequest.getToken());
        returnedSeat.setAvailable(true);

        ReturnedTicketDto returnedTicketDto = new ReturnedTicketDto(returnedSeat);

        return new ResponseEntity<>(returnedTicketDto, HttpStatus.OK);
    }

    public ResponseEntity<StatsResponse> printStats(String password) {
        if (!"super_secret".equals(password)) {
            throw new ApiException("The password is wrong!", HttpStatus.UNAUTHORIZED);
        }

        StatsResponse statsResponse;

        synchronized (tickets) {
            int currentIncome = tickets.values()
                    .stream()
                    .map(Seat::getPrice)
                    .reduce(0, Integer::sum);

            int numberOfPurchasedTickets = tickets.size();

            int availableSeats = cinemaRoom.getAvailableSeats().size() - numberOfPurchasedTickets;

            statsResponse = new StatsResponse(
                    currentIncome, availableSeats, numberOfPurchasedTickets);
        }

        return new ResponseEntity<>(statsResponse, HttpStatus.OK);
    }


}

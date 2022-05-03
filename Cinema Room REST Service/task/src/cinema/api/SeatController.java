package cinema.api;

import cinema.domain.CinemaRoom;
import cinema.domain.Seat;
import cinema.exception.ApiException;
import cinema.model.SeatRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeatController {

    private final CinemaRoom cinemaRoom = new CinemaRoom(9, 9);

    @GetMapping("/seats")
    public ResponseEntity<CinemaRoom> getSeats() {
        return new ResponseEntity<>(cinemaRoom, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Seat> buySeat(@RequestBody SeatRequestDto seatRequest) {
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

        return new ResponseEntity<>(foundSeat, HttpStatus.OK);
    }

}

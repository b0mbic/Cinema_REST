package cinema.api;

import cinema.domain.CinemaRoom;
import cinema.domain.Ticket;
import cinema.model.ReturnTicketRequest;
import cinema.model.ReturnedTicketDto;
import cinema.model.SeatRequestDto;
import cinema.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CinemaController {

    private final CinemaService service;

    @GetMapping("/seats")
    public ResponseEntity<CinemaRoom> getSeats() {
        return service.getSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> buyTicket(@RequestBody SeatRequestDto seatRequest) {
        return service.buyTicket(seatRequest);
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnedTicketDto> returnTicket(@RequestBody ReturnTicketRequest returnRequest) {
        return service.returnTicket(returnRequest);
    }

}

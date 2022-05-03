package cinema.api;

import cinema.domain.CinemaRoom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private final CinemaRoom cinemaRoom = new CinemaRoom(9, 9);

    @GetMapping
    public ResponseEntity<CinemaRoom> getSeats() {
        return new ResponseEntity<>(cinemaRoom, HttpStatus.OK);
    }

}

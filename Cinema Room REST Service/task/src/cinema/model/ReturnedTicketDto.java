package cinema.model;

import cinema.domain.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReturnedTicketDto {
    private final Seat returnedTicket;
}

package cinema.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Ticket {
    private final UUID token;
    private final Seat ticket;
}

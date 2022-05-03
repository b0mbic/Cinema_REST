package cinema.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatsResponse {
    private final int currentIncome;
    private final int numberOfAvailableSeats;
    private final int numberOfPurchasedTickets;
}

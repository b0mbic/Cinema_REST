package cinema.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CinemaRoom {
    private final int totalRows;
    private final int totalColumns;

    private final List<Seat> availableSeats;

    public CinemaRoom(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;

        final List<Seat> seats = new ArrayList<>();

        for (int i = 1; i <= totalRows; i++) {
            int price = i <= 4 ? 10 : 8;
            for (int j = 1; j <= totalColumns; j++) {
                seats.add(new Seat(i, j, price));
            }
        }
        this.availableSeats = seats;
    }

}

package cinema.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Seat {
    private final int row;
    private final int column;
}

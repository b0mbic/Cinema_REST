package cinema.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SeatRequestDto {
    private int row;
    private int column;
}

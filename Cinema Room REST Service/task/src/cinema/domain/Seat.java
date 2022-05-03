package cinema.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Seat {
    private final int row;
    private final int column;
    private final int price;

    @JsonIgnore
    private boolean available = true;
}

package za.co.wethinkcode.models.map;

import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Coordinates
{
    @NotNull
    @Min(value = 0, message = "A map's dimensions must be greater than 0.")
    @Max(value = 2147483647, message = "A map's dimensions cannot exceed MAX_INT value.")
    private int bounds;

    @NotNull
    @Min(value = 0, message = "x-coordinate cannot be less than zero.")
    @Max(value = bounds - 1, message = "x-coordinate cannot be greater than  " + bounds - 1 +".")
    private int x;

    @NotNull
    @Min(value = 0, message = "y-coordinate cannot be less than zero.")
    @Max(value = map.getSize() - 1, message = "y-coordinate cannot be greater than  " + map.getSize() +".")
    private int y;

    public Coordinates(int x, int y, int size)
    {
        this.bounds = size;
        this.setX(x);
        this.setY(y);
    }
}
package za.co.wethinkcode.swingy.models.map;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Coordinates
{
    @NotNull
    @Min(value = 0, message = "x-coordinate cannot be less than zero.")
    @Max(value = 2147483646, message = "x-coordinate cannot be greater than MAX_INT value.")
    private int x;

    @NotNull
    @Min(value = 0, message = "y-coordinate cannot be less than zero.")
    @Max(value = 2147483646, message = "y-coordinate cannot be greater than MAX_INT value.")
    private int y;

    public Coordinates(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }
}
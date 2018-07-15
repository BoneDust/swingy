package za.co.wethinkcode.models.map;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Coordinates
{
    @NotNull
    @Min(value = 0, message = "x-coordinate cannot be less than zero.")
    @Max(value = map.getSize() - 1, message = "x-coordinate cannot be greater than  " + map.getSize() +".")
    private int x;

    @NotNull
    @Min(value = 0, message = "y-coordinate cannot be less than zero.")
    @Max(value = map.getSize() - 1, message = "y-coordinate cannot be greater than  " + map.getSize() +".")
    private int y;

    @Valid
    private Map map;

    public Coordinates(int x, int y, Map map)
    {
        this.setMap(map);
        this.setX(x);
        this.setY(y);
    }
}
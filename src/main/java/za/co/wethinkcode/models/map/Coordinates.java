package za.co.wethinkcode.models.map;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Coordinates
{
    //todo create annotation that will make sure coordinates stay within the map
    @NotNull
    private int x;

    @NotNull
    private int y;

    @NotNull
    private Map map;

    public Coordinates(int x, int y, Map map)
    {
        this.setMap(map);
        this.setX(x);
        this.setY(y);
    }
}
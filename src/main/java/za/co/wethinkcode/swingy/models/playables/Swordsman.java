package za.co.wethinkcode.swingy.models.playables;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.annotations.ValidateType;
import za.co.wethinkcode.swingy.models.map.Coordinates;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class Swordsman extends Player
{
    public Swordsman(String name, String type, int level, int exp, int atk, int def, int hp,
                     Coordinates coordinates)
    {
        super(name,type, level, exp, atk, def, hp, coordinates);
    }

    public Swordsman(String name, Coordinates coordinates)
    {
        super(name, "Swordsman",1, 1000, 60, 25, 200, coordinates);
    }

}
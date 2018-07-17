package za.co.wethinkcode.models.playables;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.annotations.ValidateType;
import za.co.wethinkcode.models.map.Coordinates;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class KungFuMaster extends Player implements Playable
{
    @NotNull
    @ValidateType(types = {"KungFuMaster"})
    private String type;

    public KungFuMaster(String name, String type, int level, int exp, int atk, int def, int hp, Coordinates coordinates)
    {
        super(name, level, exp, atk, def, hp, coordinates);
        this.setType(type);
    }

    public KungFuMaster(String name, Coordinates coordinates)
    {
        super(name, 1, 0, 40, 25, 300, coordinates);
        this.setType("KungFuMaster");
    }

    public String Attack(Playable playable)
    {
        Villain villain = (Villain)playable;
        return ("Shifu "+ this.getName() + "palmed villain " + villain.getName() + " with " + this.getAtk()
                + "damage points.");
    }

    public String Defend(Playable playable)
    {
        Villain villain = (Villain)playable;
        return ("Shifu "+ this.getName() + "guarded an attack from villain " + villain.getName() + ", blocking  "
                + this.getDef() + "damage points.");
    }
}

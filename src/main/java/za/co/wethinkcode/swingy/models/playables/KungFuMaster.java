package za.co.wethinkcode.swingy.models.playables;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.annotations.ValidateType;
import za.co.wethinkcode.swingy.models.map.Coordinates;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class KungFuMaster extends Player implements Playable
{
    public KungFuMaster(int id, String name, String type, int level, int exp, int atk, int def, int hp,
                        Coordinates coordinates)
    {
        super(id, name, type, level, exp, atk, def, hp, coordinates);
    }

    public KungFuMaster(int id, String name, Coordinates coordinates)
    {
        super(id, name, "KungFuMaster",1, 1000, 40, 25, 300, coordinates);
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

package za.co.wethinkcode.swingy.models.playables;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.annotations.ValidateType;
import za.co.wethinkcode.swingy.models.map.Coordinates;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class Swordsman extends Player implements Playable
{
    public Swordsman(int id,String name, String type, int level, int exp, int atk, int def, int hp,
                     Coordinates coordinates)
    {
        super(id, name,type, level, exp, atk, def, hp, coordinates);
    }

    public Swordsman(int id, String name, Coordinates coordinates)
    {
        super(id, name, "Swordsman",1, 1000, 60, 25, 200, coordinates);
    }

    public String Attack(Playable playable)
    {
        Villain villain = (Villain)playable;
        return (this.getName() + "sliced villain " + villain.getName() + " with " + this.getAtk() + "damage points.");
    }

    public String Defend(Playable playable)
    {
        Villain villain = (Villain)playable;
        return (this.getName() + "parried an attack from villain " + villain.getName() + ", blocking  "
                + this.getDef() + "damage points.");
    }
}
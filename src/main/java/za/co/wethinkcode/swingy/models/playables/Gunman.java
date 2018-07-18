package za.co.wethinkcode.swingy.models.playables;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.annotations.ValidateType;
import za.co.wethinkcode.swingy.models.map.Coordinates;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class Gunman extends Player implements Playable
{
    @NotNull
    @ValidateType(types = {"Gunman"})
    private String type;

    public Gunman(String name, String type, int level, int exp, int atk, int def, int hp, Coordinates coordinates)
    {
        super(name, level, exp, atk, def, hp, coordinates);
        this.setType(type);
    }

    public Gunman(String name, Coordinates coordinates)
    {
        super(name, 1, 0, 80, 15, 350, coordinates);
        this.setType("Gunman");
    }

    public String Attack(Playable playable)
    {
        Villain villain = (Villain)playable;
        return (this.getName() + "shot villain " + villain.getName() + " with " + this.getAtk() + "damage points.");
    }

    public String Defend(Playable playable)
    {
        Villain villain = (Villain)playable;
        return (this.getName() + "blocked an attack from villain " + villain.getName() + ", blocking  "
                + this.getDef() + "damage points.");
    }
}
package za.co.wethinkcode.models.playables;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.annotations.ValidateType;
import za.co.wethinkcode.models.map.Coordinates;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class Gunman extends Character implements Playable
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

    public void Attack(Playable playable)
    {

    }

    public void Defend(Playable playable)
    {

    }
}
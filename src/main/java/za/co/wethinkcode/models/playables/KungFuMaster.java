package za.co.wethinkcode.models.playables;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.annotations.ValidateType;
import za.co.wethinkcode.models.map.Coordinates;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class KungFuMaster extends Character implements Playable
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

    public void Attack(Playable playable)
    {

    }

    public void Defend(Playable playable)
    {

    }
}

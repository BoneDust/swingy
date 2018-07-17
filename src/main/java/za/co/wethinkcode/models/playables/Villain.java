package za.co.wethinkcode.models.playables;


import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.annotations.ValidateType;
import za.co.wethinkcode.models.map.Coordinates;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Setter
@Getter
public class Villain extends Player
{
    @NotNull
    @Size(min = 5, max = 50, message = "A vallain's catchphrase must be  5-50 characters long.")
    private String catchPhrase;

    @NotNull
    @ValidateType(types = {"Villain"})
    private String type;


    public Villain(String name, String type, int level, int exp, int atk, int def, int hp, Coordinates pos)
    {
        super(name, level, exp, atk, def, hp, pos);
        this.setType(type);
    }

    public String Attack()
    {
        return ("Take a hit worth "+ this.getAtk()+" damage points from the mighty " + this.getName());
    }

    public String Defend()
    {
        return ("The great "+ this.getName()+" just defended an attack with " + this.getDef() + " defence points.");
    }
}

package za.co.wethinkcode.models.playables;


import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.annotations.ValidateType;
import za.co.wethinkcode.models.map.Coordinates;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Setter
@Getter
public class Villain extends Character implements Playable
{
    @NotNull
    @Size(min = 5, max = 50, message = "A vallain's catchphrase must be  5-50 characters long.")
    private String catchPhrase;

    @NotNull
    @ValidateType(types = {"Villain"})
    private String type;


    public Villain(String name, String type, int level, int exp, int atk, int def, int hp, Coordinates pos, String phrase)
    {
        super(name, level, exp, atk, def, hp, pos);
        this.setType(type);
        this.setCatchPhrase(catchPhrase);
    }

    public void Attack(Playable playable)
    {

    }

    public void Defend(Playable playable)
    {

    }
}

package za.co.wethinkcode.models.playables;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Setter
@Getter
public class Villain extends Character implements Playable
{
    @NotNull
    @Size(min = 5, max = 50, message = "A vallain's catchphrase must be  5-50 characters long.")
    private String catchPhrase;

    public Villain(String name, String type, int level, int exp, int atk, int def, int hp, String catchPhrase)
    {
        this.setName(name);
        this.setType(type);
        this.setLevel(level);
        this.setExp(exp);
        this.setAtk(atk);
        this.setDef(def);
        this.setHp(hp);
        this.setCatchPhrase(catchPhrase);
    }

    public void Attack(Playable playable)
    {

    }

    public void Defend(Playable playable)
    {

    }
}

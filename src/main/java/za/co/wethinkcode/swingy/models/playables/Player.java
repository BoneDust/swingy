package za.co.wethinkcode.swingy.models.playables;

import lombok.Getter;
import lombok.Setter;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import za.co.wethinkcode.swingy.models.artefacts.Artefact;
import za.co.wethinkcode.swingy.models.map.Coordinates;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
//http://www.summa.com/blog/2013/05/30/creating-custom-validation-constraints
public class Player
{
    @NotNull
    @Valid
    protected List<Artefact> artefacts;

    @NotNull
    @Min(value = 0, message = "Player id must be greater than 0.")
    @Max(value = 2147483647, message = "Player id cannot exceed MAX_INT value.")
    private int id;

    @NotNull
    @Size(min = 4, max = 15, message = "The length of a character's name must be between 4 and 15.")
    protected String name;

    @NotNull
    @Min(value = 0, message = "Player level cannot be lower than 0.")
    protected int level;

    @NotNull
    @Min(value = 0, message = "Player exp cannot be lower than 0.")
    protected int exp;

    @NotNull
    @Min(value = 0, message = "Player atk cannot be lower than 0.")
    protected int atk;

    @NotNull
    @Min(value = 0, message = "Player def cannot be lower than 0.")
    protected int def;

    @NotNull
    @Min(value = 0, message = "Player hp cannot be lower than 0.")
    protected int hp;

    @NotNull
    @Valid
    protected Coordinates coordinates;

    public Player(int id, String name, int level, int exp, int atk, int def, int hp, Coordinates coordinates)
    {
        this.setId(id);
        this.setName(name);
        this.setLevel(level);
        this.setExp(exp);
        this.setAtk(atk);
        this.setDef(def);
        this.setHp(hp);
        this.setCoordinates(coordinates);
        this.artefacts = new ArrayList<>();
    }
}
package za.co.wethinkcode.models.playables;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.utils.ValidateString;
import za.co.wethinkcode.models.map.Coordinates;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class Character
{
    @NotNull
    @Size(min = 4, max = 15, message = "The length of a character's name must be between 4 and 15.")
    protected String name;

    @NotNull
    @ValidateString(types = {"Villain", "KungFuMaster", "Swordsman", "Gunman"})
    protected String type;

    @NotNull
    @Min(value = 0, message = "Character level cannot be lower than 0.")
    protected int level;

    @NotNull
    @Min(value = 0, message = "Character exp cannot be lower than 0.")
    protected int exp;

    @NotNull
    @Min(value = 0, message = "Character atk cannot be lower than 0.")
    protected int atk;

    @NotNull
    @Min(value = 0, message = "Character def cannot be lower than 0.")
    protected int def;

    @NotNull
    @Min(value = 0, message = "Character hp cannot be lower than 0.")
    protected int hp;

    @NotNull
    protected Coordinates coordinates;
}

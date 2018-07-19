package za.co.wethinkcode.swingy.models.artefacts;

import lombok.Getter;
import za.co.wethinkcode.swingy.annotations.ValidateType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class Artefact
{
    @NotNull
    @Min(value = 1, message = "Artefact id cannot be less than 1.")
    @Max(value = 2147483647, message = "Artefact id cannot exceed MAX_INT value.")
    private int id;

    @NotNull
    @Max(value = 2147483647)
    @Min(value = 0)
    protected int value;
    
    @NotNull
    @Size(min = 5, max = 15)
    protected String name;

    @NotNull
    @ValidateType(types = {"Weapon", "Armor", "Helm"})
    protected String type;

    public Artefact(String name, String type, int value, int id)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
    }

}

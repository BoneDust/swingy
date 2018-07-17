package za.co.wethinkcode.models.artefacts;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.annotations.ValidateType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class Artefact
{
    @NotNull
    @Max(value = 2147483647)
    @Min(value = 0)
    protected int value;

    @Setter
    @NotNull
    @Size(min = 5, max = 15)
    protected String name;

    @Setter
    @NotNull
    @ValidateType(types = {"Weapon", "Armor", "Helm"})
    protected String type;

    public Artefact(String name, String type, int value)
    {

    }

}

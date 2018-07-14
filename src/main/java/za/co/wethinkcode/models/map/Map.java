package za.co.wethinkcode.models.map;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Map
{
    @NotNull
    @Min(value = 0, message = "A map's dimensions must be greater than 0.")
    private int size;


    @NotNull
    private int[][] grid;

    public Map(int size)
    {
        this.setSize(size);
    }

    public void initGrid()
    {
        grid = new int[this.getSize()][this.getSize()];
    }
}
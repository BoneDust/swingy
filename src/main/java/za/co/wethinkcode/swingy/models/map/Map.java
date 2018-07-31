package za.co.wethinkcode.swingy.models.map;

import lombok.Getter;
import lombok.Setter;
import za.co.wethinkcode.swingy.annotations.ValidateMapGrid;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Map
{
    @NotNull
    @Min(value = 5, message = "A map's dimensions must be at least 5.")
    @Max(value = 55, message = "A map's dimensions cannot exceed MAX map size i.e 55.")
    private int size;

    @NotNull
    @Min(value = 0, message = "Map id must be greater than 0.")
    @Max(value = 2147483647, message = "Map id cannot exceed MAX_INT value.")
    private int id;

    @ValidateMapGrid
    private int[][] grid;

    public Map(int size, int id)
    {
        this.id = id;
        this.setSize(size);
        initGrid();
    }

    private void initGrid()
    {
        grid = new int[this.getSize()][this.getSize()];
    }
}
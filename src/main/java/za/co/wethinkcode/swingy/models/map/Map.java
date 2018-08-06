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

    @ValidateMapGrid
    private int[][] grid;

    public Map(int size)
    {
        this.setSize(size);
        initGrid();
    }

    private void initGrid()
    {
        grid = new int[this.getSize()][this.getSize()];
    }
}
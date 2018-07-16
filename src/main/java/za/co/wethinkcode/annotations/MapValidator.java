package za.co.wethinkcode.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MapValidator implements ConstraintValidator<ValidateMapGrid, int[][]>
{

    @Override
    public void initialize(ValidateMapGrid constraintAnnotation)
    {
    }

    @Override
    public boolean isValid(int[][] grid, ConstraintValidatorContext context)
    {
        if (grid != null)
        {
            for (int i = 0; i < grid.length; i++)
            {
                if (grid[i] != null && grid[i].length == grid.length)
                    continue;
                else
                    return (false);
            }
            return (true);
        }
        else
            return (false);
    }
}
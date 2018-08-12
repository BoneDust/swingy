package za.co.wethinkcode.swingy.factories;

import za.co.wethinkcode.swingy.models.map.Coordinates;
import za.co.wethinkcode.swingy.models.map.Map;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class CoordinateFactory
{
    public static Coordinates newCoordinates(int x, int y, final Map map)
    {
        Coordinates coordinates = null;
        if (map == null || map.getGrid()[y][x] == 0)
            coordinates = new Coordinates(x, y);
        return (coordinates);
    }
}

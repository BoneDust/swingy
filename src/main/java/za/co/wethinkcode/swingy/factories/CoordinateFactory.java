package za.co.wethinkcode.swingy.factories;

import za.co.wethinkcode.swingy.models.map.Coordinates;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class CoordinateFactory
{
    public static Coordinates newCoordinates(int x, int y, int size)
    {
        Coordinates coordinates= new Coordinates(x, y);
        return (coordinates);
    }
}

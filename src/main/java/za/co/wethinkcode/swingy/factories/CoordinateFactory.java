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
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Coordinates coordinates= new Coordinates(x, y);

        Set<ConstraintViolation<Coordinates>> constraintViolations = validator.validate(coordinates);
        if (constraintViolations.size() > 0 )
        {
            System.out.println(ANSI_RED + "\n\n<<< Failed coordinates validations >>>\n");
            for (ConstraintViolation<Coordinates> constraints : constraintViolations)
                System.out.println("Error :" + constraints.getMessage());
            System.out.println(ANSI_RESET);
            return (null);
        }
        if (x >= size || y >= size)
        {
            System.out.println("\n\n<<< Failed coordinates validations >>>\n");
            System.out.println("Error : coordinates are out of bounds");
            return (null);
        }
        return (coordinates);
    }
}

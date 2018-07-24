package za.co.wethinkcode.swingy.factories;

import za.co.wethinkcode.swingy.models.map.Map;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class MapFactory
{
    private static int id = 0;

    private static Map validateMap(Map map)
    {
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Map>> constraintViolations = validator.validate(map);
        if (constraintViolations.size() > 0 )
        {
            System.out.println(ANSI_RED + "\n\n<<< Failed map validations >>>\n");
            for (ConstraintViolation<Map> constraints : constraintViolations)
                System.out.println("Error :" + constraints.getMessage());
            System.out.println(ANSI_RESET);
            return (null);
        }
        return (map);
    }

    public static Map newMap(int level)
    {
        id++;
        int size = ((level - 1) * 5) + 10 - (level % 2);
        Map map = new Map(size, id);
        return (validateMap(map));
    }

    public static Map oldMap(int size, int id)
    {
        Map map = new Map(size, id);
        return (validateMap(map));
    }
}

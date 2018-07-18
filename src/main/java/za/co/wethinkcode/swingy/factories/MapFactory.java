package za.co.wethinkcode.swingy.factories;

import za.co.wethinkcode.swingy.models.map.Map;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class MapFactory
{
    public static Map newMap(int level)
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        int size = ((level - 1) * 5) + 10 - (level % 2);
        Map map = new Map(size);

        Set<ConstraintViolation<Map>> constraintViolations = validator.validate(map);
        if (constraintViolations.size() > 0 )
        {
            System.out.println("\n\n<<< Failed validations >>>\n");
            for (ConstraintViolation<Map> constraints : constraintViolations)
                System.out.println("Error " + constraints.getMessage());
            return (null);
        }
        return (map);
    }
}

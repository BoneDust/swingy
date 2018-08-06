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
        int size = ((level - 1) * 5) + 10 - (level % 2);
        Map map = new Map(size);
        return (map);
    }
}

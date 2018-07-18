package za.co.wethinkcode.swingy.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

public class TypeValidator implements ConstraintValidator<ValidateType, String>
{

    private ArrayList<String> values;

    @Override
    public void initialize(ValidateType constraintAnnotation)
    {
        values = new ArrayList<>();
        for(String value : constraintAnnotation.types())
            values.add(value);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        if(!values.contains(value.toUpperCase()))
            return (false);
        return (true);
    }
}

package za.co.wethinkcode.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

public class StringValidator implements ConstraintValidator<ValidateString, String>
{

    private ArrayList<String> values;

    @Override
    public void initialize(ValidateString constraintAnnotation)
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

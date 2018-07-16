package za.co.wethinkcode.annotations;


import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = {MapValidator.class})

public @interface ValidateMapGrid
{

    String message() default "Invalid map grid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}


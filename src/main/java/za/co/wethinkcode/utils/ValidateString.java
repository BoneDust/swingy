//https://stackoverflow.com/questions/6294587/java-string-validation-using-enum-values-and-annotation/6360458

package za.co.wethinkcode.utils;

import javax.validation.Payload;

public @interface ValidateString
{
    String[] types();

    String message() default "Invalid character type";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

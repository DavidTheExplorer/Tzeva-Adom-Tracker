package dte.tzevaadomtracker.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PersonalTokenValidator.class)
public @interface PersonalToken
{
    boolean existing() default true;
    String message() default "Personal Token not found";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

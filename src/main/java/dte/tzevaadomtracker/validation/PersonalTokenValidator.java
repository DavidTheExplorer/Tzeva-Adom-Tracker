package dte.tzevaadomtracker.validation;

import dte.tzevaadomtracker.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

public class PersonalTokenValidator implements ConstraintValidator<PersonalToken, String>
{
    private final UserService userService;

    private boolean shouldExist;

    public PersonalTokenValidator(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public void initialize(PersonalToken annotation)
    {
        this.shouldExist = annotation.existing();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        if(value == null)
            return false;

        UUID token;

        try
        {
            token = UUID.fromString(value);
        }
        catch(IllegalArgumentException exception)
        {
            return false;
        }

        return this.userService.existsByToken(token) == this.shouldExist;
    }
}
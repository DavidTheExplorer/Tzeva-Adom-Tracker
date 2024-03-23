package dte.tzevaadomtracker.dto.requests;

import dte.tzevaadomtracker.validation.PersonalToken;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record EndpointRegistrationRequest(
        @NotNull(message = "Personal token must be provided")
        @PersonalToken String personalToken,

        @NotNull(message = "Endpoint URL must be provided")
        @URL(message = "Endpoint URL must be valid")
        String url)
{

}

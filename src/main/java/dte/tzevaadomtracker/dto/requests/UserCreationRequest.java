package dte.tzevaadomtracker.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreationRequest(
        @NotNull(message = "First name must be provided")
        @Size(min = 2, max = 20, message = "First name should be between 2-20 characters")
        String firstName)
{

}

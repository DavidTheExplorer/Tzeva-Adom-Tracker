package dte.tzevaadomtracker.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(
        @JsonProperty("errors")
        String... errorMessages)
{

}

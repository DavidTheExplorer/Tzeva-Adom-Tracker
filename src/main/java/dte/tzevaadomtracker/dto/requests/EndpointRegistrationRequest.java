package dte.tzevaadomtracker.dto.requests;

import java.util.UUID;

public record EndpointRegistrationRequest(UUID personalToken, String url)
{

}

package dte.tzevaadomtracker.webclient;

import dte.tzevaadomtracker.alertendpoint.AlertEndpoint;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class UserTokenClientFactory implements WebClientFactory
{
    @Override
    public WebClient getClientFor(AlertEndpoint endpoint)
    {
        String ownerPersonalToken = endpoint.getOwner().getPersonalToken().toString();

        return WebClient.builder()
                .defaultHeader(AUTHORIZATION, String.format("Token %s", ownerPersonalToken))
                .baseUrl(endpoint.getURL())
                .build();
    }
}

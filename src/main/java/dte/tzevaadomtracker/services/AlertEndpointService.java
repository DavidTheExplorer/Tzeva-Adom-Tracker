package dte.tzevaadomtracker.services;

import dte.tzevaadomtracker.alertendpoint.AlertEndpoint;
import dte.tzevaadomtracker.dto.requests.EndpointRegistrationRequest;
import dte.tzevaadomtracker.repositories.AlertEndpointRepository;

import dte.tzevaadomtracker.user.User;
import jakarta.annotation.PostConstruct;
import org.atteo.evo.inflector.English;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AlertEndpointService
{
    private final AlertEndpointRepository alertEndpointRepository;
    private final UserService userService;
    private List<AlertEndpoint> endpoints;

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertEndpointService.class);

    public AlertEndpointService(UserService userService, AlertEndpointRepository alertEndpointRepository)
    {
        this.userService = userService;
        this.alertEndpointRepository = alertEndpointRepository;
    }

    @PostConstruct
    private void loadAllEndpoints()
    {
        this.endpoints = new ArrayList<>(this.alertEndpointRepository.findAll());

        LOGGER.info("Loaded {} {} from the database.", this.endpoints.size(), English.plural("endpoint", this.endpoints.size()));
    }

    public void registerEndpoint(EndpointRegistrationRequest request)
    {
        String url = request.url();

        if(this.alertEndpointRepository.existsByUrl(url))
            throw new IllegalArgumentException(String.format("Endpoint '%s' is already registered!", url));

        User owner = this.userService.findByPersonalToken(request.personalToken());

        //save the endpoint in the database
        AlertEndpoint endpoint = this.alertEndpointRepository.save(new AlertEndpoint(url, owner));

        //cache the endpoint
        this.endpoints.add(endpoint);
    }

    public long getEndpointsAmount()
    {
        return this.endpoints.size();
    }

    public Iterable<AlertEndpoint> getEndpoints()
    {
        return Collections.unmodifiableList(this.endpoints); //faster than returning a copy
    }
}

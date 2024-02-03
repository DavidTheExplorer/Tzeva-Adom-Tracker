package dte.tzevaadomtracker.services;

import dte.tzevaadomtracker.alertendpoint.AlertEndpoint;
import dte.tzevaadomtracker.repositories.AlertEndpointRepository;

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
    private List<AlertEndpoint> endpoints;

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertEndpointService.class);

    public AlertEndpointService(AlertEndpointRepository alertEndpointRepository)
    {
        this.alertEndpointRepository = alertEndpointRepository;
    }

    @PostConstruct
    private void loadAllEndpoints()
    {
        this.endpoints = new ArrayList<>(this.alertEndpointRepository.findAll());

        LOGGER.info("Loaded {} {} from the database.", this.endpoints.size(), English.plural("endpoint", this.endpoints.size()));
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

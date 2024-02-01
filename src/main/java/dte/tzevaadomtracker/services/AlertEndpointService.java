package dte.tzevaadomtracker.services;

import dte.tzevaadomapi.alert.Alert;
import dte.tzevaadomtracker.alertendpoint.AlertEndpoint;
import dte.tzevaadomtracker.events.TzevaAdomEvent;
import dte.tzevaadomtracker.repositories.AlertEndpointRepository;
import dte.tzevaadomtracker.webclient.WebClientFactory;

import jakarta.annotation.PostConstruct;
import org.atteo.evo.inflector.English;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertEndpointService
{
    private final AlertEndpointRepository alertEndpointRepository;
    private final WebClientFactory webClientFactory;

    private final List<AlertEndpoint> endpoints = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertEndpointService.class);

    public AlertEndpointService(AlertEndpointRepository alertEndpointRepository, WebClientFactory webClientFactory)
    {
        this.alertEndpointRepository = alertEndpointRepository;
        this.webClientFactory = webClientFactory;
    }

    @PostConstruct
    public void loadAllEndpoints()
    {
        this.endpoints.addAll(this.alertEndpointRepository.findAll());

        LOGGER.info("Loaded {} {} from the database.", this.endpoints.size(), English.plural("endpoint", this.endpoints.size()));
    }

    @Async
    @EventListener
    public void onTzevaAdom(TzevaAdomEvent event)
    {
        Alert alert = event.getSource();

        //log the Tzeva Adom
        LOGGER.info("Notifying {} {} about a Tzeva Adom in {}!", this.endpoints.size(), English.plural("endpoint", this.endpoints.size()), alert.getRegion());

        //notify the users' servers
        this.endpoints.forEach(server -> notifyTzevaAdom(server, alert));
    }

    public void notifyTzevaAdom(AlertEndpoint endpoint, Alert alert)
    {
        this.webClientFactory.getClientFor(endpoint)
                .post()
                .body(Mono.just(alert), Alert.class)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(throwable -> LOGGER.error("Couldn't notify {} about a Tzeva Adom - {}", endpoint.getURL(), throwable.getMessage()))
                .toFuture();
    }

    public long getEndpointsAmount()
    {
        return this.endpoints.size();
    }
}

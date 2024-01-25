package dte.tzevaadomtracker.services;

import dte.tzevaadomapi.alert.Alert;
import dte.tzevaadomtracker.alertendpoint.AlertEndpoint;
import dte.tzevaadomtracker.events.TzevaAdomEvent;
import dte.tzevaadomtracker.repositories.AlertEndpointRepository;
import dte.tzevaadomtracker.webclient.WebClientFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AlertEndpointService
{
    private final AlertEndpointRepository alertEndpointRepository;
    private final WebClientFactory webClientFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertEndpointService.class);

    public AlertEndpointService(AlertEndpointRepository alertEndpointRepository, WebClientFactory webClientFactory)
    {
        this.alertEndpointRepository = alertEndpointRepository;
        this.webClientFactory = webClientFactory;
    }

    @Async
    @EventListener
    public void onTzevaAdom(TzevaAdomEvent event)
    {
        Alert alert = event.getSource();
        List<AlertEndpoint> usersEndpoints = this.alertEndpointRepository.findAll();

        //log the Tzeva Adom
        LOGGER.info("Notifying {} server(s) about a Tzeva Adom in {}!", this.alertEndpointRepository.count(), alert.getRegion());

        //notify the users' servers
        usersEndpoints.forEach(server -> notifyTzevaAdom(server, alert));
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
        return this.alertEndpointRepository.count();
    }
}

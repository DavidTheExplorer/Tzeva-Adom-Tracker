package dte.tzevaadomtracker.listeners;

import dte.tzevaadomapi.alert.Alert;
import dte.tzevaadomtracker.alertendpoint.notifier.AlertEndpointNotifier;
import dte.tzevaadomtracker.events.TzevaAdomEvent;
import dte.tzevaadomtracker.services.AlertEndpointService;

import org.atteo.evo.inflector.English;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TzevaAdomPublishingListener
{
    private final AlertEndpointService alertEndpointService;
    private final AlertEndpointNotifier alertEndpointNotifier;

    private static final Logger LOGGER = LoggerFactory.getLogger(TzevaAdomPublishingListener.class);

    public TzevaAdomPublishingListener(AlertEndpointService alertEndpointService, AlertEndpointNotifier alertEndpointNotifier)
    {
        this.alertEndpointService = alertEndpointService;
        this.alertEndpointNotifier = alertEndpointNotifier;
    }

    @Async
    @EventListener
    public void onTzevaAdom(TzevaAdomEvent event)
    {
        Alert alert = event.getAlert();

        logToConsole(alert);
        notifyEndpoints(alert);
    }

    private void logToConsole(Alert alert)
    {
        int endpointsAmount = (int) this.alertEndpointService.getEndpointsAmount();

        LOGGER.info("Notifying {} {} about a Tzeva Adom in {}!", endpointsAmount, English.plural("endpoint", endpointsAmount), alert.getRegion());
    }

    private void notifyEndpoints(Alert alert)
    {
        this.alertEndpointService.getEndpoints().forEach(endpoint -> this.alertEndpointNotifier.notifyTzevaAdom(endpoint, alert));
    }
}
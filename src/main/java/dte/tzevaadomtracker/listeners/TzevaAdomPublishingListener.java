package dte.tzevaadomtracker.listeners;

import dte.tzevaadomapi.alert.Alert;
import dte.tzevaadomtracker.alertendpoint.AlertEndpoint;
import dte.tzevaadomtracker.alertendpoint.notifier.AlertEndpointNotifier;
import dte.tzevaadomtracker.events.TzevaAdomEvent;
import dte.tzevaadomtracker.services.AlertEndpointService;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TzevaAdomPublishingListener
{
    private final AlertEndpointService alertEndpointService;
    private final AlertEndpointNotifier alertEndpointNotifier;

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

        for(AlertEndpoint endpoint : this.alertEndpointService.getEndpoints())
            this.alertEndpointNotifier.notifyTzevaAdom(endpoint, alert);
    }
}
package dte.tzevaadomtracker.listeners;

import dte.tzevaadomtracker.events.TzevaAdomEvent;
import dte.tzevaadomtracker.services.AlertService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TzevaAdomTrackingListener
{
    private final AlertService alertService;

    public TzevaAdomTrackingListener(AlertService alertService)
    {
        this.alertService = alertService;
    }

    @EventListener
    public void onTzevaAdom(TzevaAdomEvent event)
    {
        this.alertService.track(event.getSource());
    }
}

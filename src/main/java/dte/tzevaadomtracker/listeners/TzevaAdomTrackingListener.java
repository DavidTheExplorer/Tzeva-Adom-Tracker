package dte.tzevaadomtracker.listeners;

import dte.tzevaadomtracker.alert.AlertEntity;
import dte.tzevaadomtracker.dto.mappers.AlertMapper;
import dte.tzevaadomtracker.events.TzevaAdomEvent;
import dte.tzevaadomtracker.services.AlertService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TzevaAdomTrackingListener
{
    private final AlertService alertService;
    private final AlertMapper alertMapper;

    public TzevaAdomTrackingListener(AlertService alertService, AlertMapper alertMapper)
    {
        this.alertService = alertService;
        this.alertMapper = alertMapper;
    }

    @EventListener
    public void onTzevaAdom(TzevaAdomEvent event)
    {
        AlertEntity alertEntity = this.alertMapper.toEntity(event.getSource());

        this.alertService.track(alertEntity);
    }
}

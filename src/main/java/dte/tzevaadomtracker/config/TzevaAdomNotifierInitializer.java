package dte.tzevaadomtracker.config;

import dte.tzevaadomapi.alert.Alert;
import dte.tzevaadomapi.notifier.TzevaAdomNotifier;
import dte.tzevaadomtracker.alert.AlertEntity;
import dte.tzevaadomtracker.dto.mappers.AlertMapper;
import dte.tzevaadomtracker.events.TzevaAdomEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TzevaAdomNotifierInitializer
{
    private final ApplicationEventPublisher eventPublisher;
    private final AlertMapper alertMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(TzevaAdomNotifier.class);

    public TzevaAdomNotifierInitializer(ApplicationEventPublisher eventPublisher, AlertMapper alertMapper)
    {
        this.eventPublisher = eventPublisher;
        this.alertMapper = alertMapper;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startEventPublisherNotifier()
    {
        TzevaAdomNotifier notifier = new TzevaAdomNotifier.Builder()
                .onTzevaAdom(this::publishEvent)
                .onFailedRequest(exception -> LOGGER.error("Could not check if it's Tzeva Adom - {}", exception.getMessage()))
                .build();

        notifier.listenAsync();
    }

    private void publishEvent(Alert alert)
    {
        AlertEntity alertEntity = this.alertMapper.toEntity(alert);

        this.eventPublisher.publishEvent(new TzevaAdomEvent(alertEntity));
    }
}
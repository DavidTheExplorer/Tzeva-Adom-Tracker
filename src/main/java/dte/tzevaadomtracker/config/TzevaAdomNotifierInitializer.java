package dte.tzevaadomtracker.config;

import dte.tzevaadomapi.notifier.TzevaAdomNotifier;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(TzevaAdomNotifier.class);

    public TzevaAdomNotifierInitializer(ApplicationEventPublisher eventPublisher)
    {
        this.eventPublisher = eventPublisher;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startNotifier()
    {
        createEventPublisherNotifier().listenAsync();
    }

    private TzevaAdomNotifier createEventPublisherNotifier()
    {
        return new TzevaAdomNotifier.Builder()
                .onTzevaAdom(alert -> this.eventPublisher.publishEvent(new TzevaAdomEvent(alert)))
                .onFailedRequest(exception -> LOGGER.error("Could not check if it's Tzeva Adom - {}", exception.getMessage()))
                .build();
    }
}
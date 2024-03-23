package dte.tzevaadomtracker.listeners;

import dte.tzevaadomtracker.events.TzevaAdomEvent;
import dte.tzevaadomtracker.services.AlertEndpointService;
import org.atteo.evo.inflector.English;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TzevaAdomLogListener
{
    private final AlertEndpointService alertEndpointService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TzevaAdomLogListener.class);

    public TzevaAdomLogListener(AlertEndpointService alertEndpointService)
    {
        this.alertEndpointService = alertEndpointService;
    }

    @EventListener
    public void onTzevaAdom(TzevaAdomEvent event)
    {
        String region = event.getAlert().getRegion();
        int endpointsAmount = (int) this.alertEndpointService.getEndpointsAmount();

        LOGGER.info("Notifying {} {} about a Tzeva Adom in {}!", endpointsAmount, English.plural("endpoint", endpointsAmount), region);
    }
}

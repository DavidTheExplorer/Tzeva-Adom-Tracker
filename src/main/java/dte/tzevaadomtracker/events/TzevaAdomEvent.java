package dte.tzevaadomtracker.events;

import dte.tzevaadomtracker.alert.AlertEntity;
import org.springframework.context.ApplicationEvent;

public class TzevaAdomEvent extends ApplicationEvent
{
    public TzevaAdomEvent(AlertEntity alert)
    {
        super(alert);
    }

    @Override
    public AlertEntity getSource()
    {
        return (AlertEntity) super.getSource();
    }
}

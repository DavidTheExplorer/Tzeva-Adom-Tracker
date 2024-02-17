package dte.tzevaadomtracker.events;

import dte.tzevaadomapi.alert.Alert;
import org.springframework.context.ApplicationEvent;

public class TzevaAdomEvent extends ApplicationEvent
{
    public TzevaAdomEvent(Alert alert)
    {
        super(alert);
    }

    @Override
    public Alert getSource()
    {
        return (Alert) super.getSource();
    }
}

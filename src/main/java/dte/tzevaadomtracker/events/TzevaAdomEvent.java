package dte.tzevaadomtracker.events;

import dte.tzevaadomapi.alert.Alert;
import org.springframework.context.ApplicationEvent;

public class TzevaAdomEvent extends ApplicationEvent
{
    private final Alert alert;

    public TzevaAdomEvent(Alert alert)
    {
        super(alert);

        this.alert = alert;
    }

    public Alert getAlert()
    {
        return this.alert;
    }
}

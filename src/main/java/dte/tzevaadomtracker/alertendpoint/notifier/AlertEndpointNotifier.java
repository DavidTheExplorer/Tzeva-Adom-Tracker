package dte.tzevaadomtracker.alertendpoint.notifier;

import dte.tzevaadomtracker.alert.AlertEntity;
import dte.tzevaadomtracker.alertendpoint.AlertEndpoint;

@FunctionalInterface
public interface AlertEndpointNotifier
{
    void notifyTzevaAdom(AlertEndpoint endpoint, AlertEntity alert);
}

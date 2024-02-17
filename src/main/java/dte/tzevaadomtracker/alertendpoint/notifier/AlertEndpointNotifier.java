package dte.tzevaadomtracker.alertendpoint.notifier;

import dte.tzevaadomapi.alert.Alert;
import dte.tzevaadomtracker.alertendpoint.AlertEndpoint;

@FunctionalInterface
public interface AlertEndpointNotifier
{
    void notifyTzevaAdom(AlertEndpoint endpoint, Alert alert);
}

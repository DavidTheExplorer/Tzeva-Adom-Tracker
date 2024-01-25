package dte.tzevaadomtracker.webclient;

import dte.tzevaadomtracker.alertendpoint.AlertEndpoint;

import org.springframework.web.reactive.function.client.WebClient;

@FunctionalInterface
public interface WebClientFactory
{
    WebClient getClientFor(AlertEndpoint endpoint);
}

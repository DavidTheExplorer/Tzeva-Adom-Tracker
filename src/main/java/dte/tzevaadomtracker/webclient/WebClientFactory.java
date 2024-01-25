package dte.tzevaadomtracker.webclient;

import dte.tzevaadomtracker.alertendpoint.AlertEndpoint;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.reactive.function.client.WebClient;

@FunctionalInterface
public interface WebClientFactory
{
    @Cacheable("web-clients")
    WebClient getClientFor(AlertEndpoint endpoint);
}

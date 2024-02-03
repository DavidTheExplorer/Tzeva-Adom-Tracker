package dte.tzevaadomtracker.config;

import dte.tzevaadomtracker.alertendpoint.notifier.AlertEndpointNotifier;
import dte.tzevaadomtracker.alertendpoint.notifier.TokenBasedNotifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlertEndpointNotifierConfig
{
    @Bean
    AlertEndpointNotifier alertEndpointNotifier()
    {
        return new TokenBasedNotifier();
    }
}
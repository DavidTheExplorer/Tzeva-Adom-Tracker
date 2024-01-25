package dte.tzevaadomtracker.config;

import dte.tzevaadomtracker.webclient.UserTokenClientFactory;
import dte.tzevaadomtracker.webclient.WebClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig
{
    @Bean
    WebClientFactory webClientFactory()
    {
        return new UserTokenClientFactory();
    }
}

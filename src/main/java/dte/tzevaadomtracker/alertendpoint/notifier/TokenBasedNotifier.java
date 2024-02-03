package dte.tzevaadomtracker.alertendpoint.notifier;

import dte.tzevaadomapi.alert.Alert;
import dte.tzevaadomtracker.alertendpoint.AlertEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TokenBasedNotifier implements AlertEndpointNotifier
{
    @Autowired
    private TokenBasedNotifier self;

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenBasedNotifier.class);

    @Override
    public void notifyTzevaAdom(AlertEndpoint endpoint, Alert alert)
    {
        this.self.getClientOf(endpoint)
                .post()
                .body(Mono.just(alert), Alert.class)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnError(throwable -> LOGGER.error("Couldn't notify {} about a Tzeva Adom - {}", endpoint.getURL(), throwable.getMessage()))
                .toFuture();
    }

    @Cacheable(value = "webclients", key = "#endpoint.getURL()")
    public WebClient getClientOf(AlertEndpoint endpoint)
    {
        String ownerPersonalToken = endpoint.getOwner().getPersonalToken().toString();

        return WebClient.builder()
                .defaultHeader(AUTHORIZATION, String.format("Token %s", ownerPersonalToken))
                .baseUrl(endpoint.getURL())
                .build();
    }
}

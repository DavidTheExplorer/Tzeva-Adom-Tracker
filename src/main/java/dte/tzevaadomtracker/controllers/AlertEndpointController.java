package dte.tzevaadomtracker.controllers;

import dte.tzevaadomtracker.dto.requests.EndpointRegistrationRequest;
import dte.tzevaadomtracker.services.AlertEndpointService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/endpoints")
public class AlertEndpointController
{
    private final AlertEndpointService alertEndpointService;

    public AlertEndpointController(AlertEndpointService alertEndpointService)
    {
        this.alertEndpointService = alertEndpointService;
    }

    @PostMapping
    public ResponseEntity<Void> registerEndpoint(@RequestBody @Valid EndpointRegistrationRequest request)
    {
        this.alertEndpointService.registerEndpoint(request);

        return ResponseEntity.status(CREATED).build();
    }
}

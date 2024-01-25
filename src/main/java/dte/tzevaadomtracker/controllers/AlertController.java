package dte.tzevaadomtracker.controllers;

import dte.tzevaadomtracker.dto.AlertDTO;
import dte.tzevaadomtracker.dto.mappers.AlertMapper;
import dte.tzevaadomtracker.dto.requests.AlertHistoryRequest;
import dte.tzevaadomtracker.services.AlertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/alerts-history")
public class AlertController
{
    private final AlertService alertService;
    private final AlertMapper alertMapper;

    public AlertController(AlertService alertService, AlertMapper alertMapper)
    {
        this.alertService = alertService;
        this.alertMapper = alertMapper;
    }

    @GetMapping
    public List<AlertDTO> searchAlerts(@ModelAttribute AlertHistoryRequest request)
    {
        return this.alertService.findBy(request).stream()
                .map(this.alertMapper::toDTO)
                .toList();
    }
}

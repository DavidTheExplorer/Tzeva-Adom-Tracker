package dte.tzevaadomtracker.controllers;

import dte.tzevaadomtracker.dto.responses.StatisticsResponse;
import dte.tzevaadomtracker.services.AlertEndpointService;

import dte.tzevaadomtracker.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/stats")
public class StatisticsController
{
    private final UserService userService;
    private final AlertEndpointService alertEndpointService;

    public StatisticsController(UserService userService, AlertEndpointService alertEndpointService)
    {
        this.userService = userService;
        this.alertEndpointService = alertEndpointService;
    }

    @GetMapping
    public StatisticsResponse getStats()
    {
        long usersAmount = this.userService.getUsersAmount();
        long serversAmount = this.alertEndpointService.getEndpointsAmount();

        return new StatisticsResponse(usersAmount, serversAmount);
    }
}

package dte.tzevaadomtracker.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StatisticsResponse(
        @JsonProperty("users") Long usersAmount,
        @JsonProperty("servers") Long serversAmount)
{

}

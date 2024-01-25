package dte.tzevaadomtracker.dto.requests;

import java.time.LocalDateTime;

public record AlertHistoryRequest(String region, Integer category, LocalDateTime startDate, LocalDateTime endDate)
{

}
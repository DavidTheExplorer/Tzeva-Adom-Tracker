package dte.tzevaadomtracker.dto;

import java.time.LocalDateTime;

public record AlertDTO(LocalDateTime date, String title, String region, int category)
{

}

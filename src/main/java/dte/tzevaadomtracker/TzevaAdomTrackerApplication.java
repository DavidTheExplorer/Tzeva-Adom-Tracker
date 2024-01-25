package dte.tzevaadomtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TzevaAdomTrackerApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(TzevaAdomTrackerApplication.class, args);
	}
}

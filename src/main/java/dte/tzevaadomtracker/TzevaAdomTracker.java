package dte.tzevaadomtracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCaching
public class TzevaAdomTracker
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TzevaAdomTracker.class);

	public static void main(String[] args)
	{
		SpringApplication.run(TzevaAdomTracker.class, args);

		LOGGER.info("Listening to Tzeva Adoms...");
	}
}
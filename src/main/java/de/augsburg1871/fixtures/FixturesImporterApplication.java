package de.augsburg1871.fixtures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class FixturesImporterApplication {

	public static void main(final String[] args) {
		SpringApplication.run(FixturesImporterApplication.class, args);
	}
}

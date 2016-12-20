package de.augsburg1871.fixtures.flow.delta;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;

import de.augsburg1871.fixtures.flow.CSVReader;
import de.augsburg1871.fixtures.service.GamesService;

@Configuration
public class DeltaUpdateFlow {

	@Bean
	IntegrationFlow readFile(
			final GamesService gameService,
			@Value("${fixtures.current-season}") final String currentSeason) {
		return IntegrationFlows.from(Files.inboundAdapter(new File("src/main/resources"))
				.patternFilter("*_Regionsspielplan.csv").preventDuplicates(), c -> c.poller(Pollers.fixedDelay(1000)))
				.handle(new CSVReader())
				.split()
				.filter(new LeagueRelayFilter())
				.transform(new CSVRecordToGameTransformer(currentSeason))
				.handle(new UpsertHandler(gameService, currentSeason))
				.get();
	}

}

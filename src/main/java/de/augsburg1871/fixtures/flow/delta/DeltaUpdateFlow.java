package de.augsburg1871.fixtures.flow.delta;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;

import de.augsburg1871.fixtures.flow.CSVReader;
import de.augsburg1871.fixtures.repository.GameRepository;

@Configuration
public class DeltaUpdateFlow {

	@Bean
	IntegrationFlow readFile(
			@Value("#{'${fixtures.public.valid.teams}'.split(',')}") final List<String> teamsToImport,
			final GameRepository gameRepository,
			@Value("${fixtures.current-season}") final String currentSeason) {
		return IntegrationFlows.from(Files.inboundAdapter(new File("src/main/resources"))
				.patternFilter("*_Regionsspielplan.csv").preventDuplicates(), c -> c.poller(Pollers.fixedDelay(1000)))
				.handle(new CSVReader())
				.split()
				.handle(new CSVRecordToGameTransformer(currentSeason))
				.filter(new GameByTeamsFilter(teamsToImport))
				.handle(new UpsertHandler(gameRepository, currentSeason))
				.get();
	}

}

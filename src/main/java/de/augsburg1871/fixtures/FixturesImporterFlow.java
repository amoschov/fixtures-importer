package de.augsburg1871.fixtures;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;

import com.google.common.collect.Lists;

@Configuration
public class FixturesImporterFlow {

	private final Log logger = LogFactory.getLog(FixturesImporterFlow.class);

	@Bean
	IntegrationFlow readFile(
			@Value("#{'${fixtures.public.valid.teams}'.split(',')}") final List<String> teamsToImport) {

		return IntegrationFlows.from(Files.inboundAdapter(new File("src/main/resources"))
				.patternFilter("*_Regionsspielplan.csv").preventDuplicates(), c -> c.poller(Pollers.fixedDelay(1000)))
				.handle((p, h) -> {
					try {
						final FileInputStream fileInputStream = new FileInputStream((File) p);
						final Reader reader = new InputStreamReader(fileInputStream, StandardCharsets.ISO_8859_1);
						return CSVFormat.EXCEL.withFirstRecordAsHeader()
								.withIgnoreEmptyLines()
								.withDelimiter(';')
								.parse(reader)
								.getRecords();
					} catch (final IOException e) {
						logger.error("Datei konnte nicht verarbeitet werden.", e);
					}
					return Lists.<CSVRecord>newArrayList();
				})
				.split()
				.transform(new CSVRecordToPojoTransformer())
				.filter(new CsvLineFilter(teamsToImport))
				// TODO: write to db
				.handle(m -> System.out.println(LocalDateTime.now() + " -- " + m.getPayload()))
				.get();
	}

}

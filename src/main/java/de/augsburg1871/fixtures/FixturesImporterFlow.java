package de.augsburg1871.fixtures;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;

import com.google.common.collect.Lists;

import de.augsburg1871.fixtures.persistence.model.Fixture;
import de.augsburg1871.fixtures.persistence.model.Fixture.FixtureBuilder;

@Configuration
public class FixturesImporterFlow {

	private final Log logger = LogFactory.getLog(FixturesImporterFlow.class);

	@Bean
	IntegrationFlow readFile() {
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
				.filter(new CsvLineFilter())
				// TODO: write to db
				.handle(m -> System.out.println(LocalDateTime.now() + " -- " + m.getPayload()))
				.get();
	}

	public static class CsvLineFilter {

		private final List<String> teamsToImport = Lists.newArrayList("Augsburg 1871", "Augsburg 1871 II",
				"SG Augsburg-Gersthofen");

		@Filter
		public boolean isValid(final Fixture fixture) {

			if (teamsToImport.contains(fixture.getHome())) {
				return true;
			} else if (teamsToImport.contains(fixture.getAway())) {
				return true;
			}

			return false;
		}

	}

	public static class CSVRecordToPojoTransformer {

		@Transformer
		public Fixture transform(final CSVRecord record) {

			final LocalDate date = LocalDate.parse(record.get("Datum"), new DateTimeFormatterBuilder()
					.appendValue(DAY_OF_MONTH, 2)
					.appendLiteral('.')
					.appendValue(MONTH_OF_YEAR, 2)
					.appendLiteral('.')
					.appendValue(YEAR, 4)
					.toFormatter(Locale.GERMANY));
			final LocalTime time = LocalTime.parse(record.get("Zeit"), DateTimeFormatter.ISO_LOCAL_TIME);

			return new FixtureBuilder()
					.localDateTime(LocalDateTime.of(date, time))
					.home(record.get("Heimmannschaft"))
					.away(record.get("Gastmannschaft"))
					.build();
		}

	}

}

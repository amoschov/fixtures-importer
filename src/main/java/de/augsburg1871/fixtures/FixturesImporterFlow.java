package de.augsburg1871.fixtures;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.file.Files;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import de.augsburg1871.fixtures.exceptions.MissingMandatoryColumnException;
import de.augsburg1871.fixtures.persistence.model.Fixture;
import de.augsburg1871.fixtures.persistence.model.Fixture.FixtureBuilder;

@Configuration
public class FixturesImporterFlow {

	@Bean
	IntegrationFlow readFile() {
		return IntegrationFlows.from(Files.inboundAdapter(new File("C:\\Users\\Benni\\Downloads\\read"))
				.patternFilter("*_Regionsspielplan.csv").preventDuplicates(), c -> c.poller(Pollers.fixedDelay(1000)))
				.handle(new ColumnDetector())
				.split(new CSVSplitter())
				.transform(new CsvToPojoTransformer())
				.filter(new CsvLineFilter())
				.handle(m -> System.out.println(m.getPayload()))
				.get();
	}

	public static class CsvLineFilter {

		private final List<String> teamsToImport = Lists.newArrayList("Augsburg 1871", "Augsburg 1871 II",
				"SG Augsburg-Gersthofen");

		@Filter
		public boolean isValid(final Fixture fixture) {

			if (teamsToImport.contains(fixture.getHome())) {
				// System.out.println(fixture + "VALID");
				return true;
			} else if (teamsToImport.contains(fixture.getAway())) {
				// System.out.println(fixture + "VALID");
				return true;
			}

			// System.out.println(fixture + "INVALID");
			return false;
		}

	}

	public static class CsvToPojoTransformer {

		@Transformer
		public Fixture transform(final String line) {
			final List<String> columns = Arrays.asList(line.split(";"));

			final LocalDate date = LocalDate.parse(columns.get(1), new DateTimeFormatterBuilder()
					.appendValue(DAY_OF_MONTH, 2)
					.appendLiteral('.')
					.appendValue(MONTH_OF_YEAR, 2)
					.appendLiteral('.')
					.appendValue(YEAR, 4)
					.toFormatter(Locale.GERMANY));
			final LocalTime time = LocalTime.parse(columns.get(2), DateTimeFormatter.ISO_LOCAL_TIME);

			return new FixtureBuilder()
					.localDateTime(LocalDateTime.of(date, time))
					.home(columns.get(8))
					.away(columns.get(9))
					.build();
		}

	}

	public static class CSVSplitter {

		private int linesToSkip = 0;
		private boolean includeHeader = false;

		public CSVSplitter skipLines(final int linesToSkip) {
			this.linesToSkip = linesToSkip;
			return this;
		}

		public CSVSplitter includeHeader(final boolean includeHeader) {
			this.includeHeader = includeHeader;
			return this;
		}

		@Splitter
		public List<String> split(final File file) throws IOException {
			final List<String> lines = java.nio.file.Files.readAllLines(file.toPath(), StandardCharsets.ISO_8859_1);
			lines.remove(0);
			return lines;
		}

	}

	public static class ColumnDetector {

		@ServiceActivator(requiresReply = "true")
		public Message<File> hasMandatoryColumns(final File file) throws IOException {
			final List<String> mandatoryColumns = Lists.newArrayList("Tag", "Datum", "Heimmannschaft",
					"Gastmannschaft");
			final Message<File> message = MessageBuilder.withPayload(file).build();
			final List<String> lines = java.nio.file.Files.readAllLines(file.toPath(), StandardCharsets.ISO_8859_1);

			if (CollectionUtils.isEmpty(lines)) {
				return message;
			}

			final List<String> readHeadlines = Arrays.asList(lines.get(0).split(";"));
			for (final String mandatoryColumn : mandatoryColumns) {
				if (!readHeadlines.contains(mandatoryColumn)) {
					throw new MissingMandatoryColumnException(mandatoryColumn);
				}
			}

			return message;
		}

	}

}

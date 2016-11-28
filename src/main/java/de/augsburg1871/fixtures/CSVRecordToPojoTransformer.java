package de.augsburg1871.fixtures;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import org.apache.commons.csv.CSVRecord;
import org.springframework.integration.annotation.Transformer;

import de.augsburg1871.fixtures.persistence.model.Fixture;
import de.augsburg1871.fixtures.persistence.model.Fixture.FixtureBuilder;

public class CSVRecordToPojoTransformer {

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
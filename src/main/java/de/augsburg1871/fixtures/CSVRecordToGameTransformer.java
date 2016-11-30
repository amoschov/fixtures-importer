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

import de.augsburg1871.fixtures.persistence.model.Game;

public class CSVRecordToGameTransformer {

	@Transformer
	public Game transform(final CSVRecord record) {

		final LocalDate date = parseDate(record.get("Datum"));
		final LocalTime time = parseTime(record.get("Zeit"));

		final Game game = Game.builder()
				.localDateTime(LocalDateTime.of(date, time))
				.gameNumber(record.get("Spielnummer"))
				.gymNumber(record.get("Hallennummer"))
				.season("2016/17")
				.home(record.get("Heimmannschaft"))
				.away(record.get("Gastmannschaft"))
				.build();
		return game;
	}

	private LocalTime parseTime(final String time) {
		return LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
	}

	private LocalDate parseDate(final String date) {
		return LocalDate.parse(date, new DateTimeFormatterBuilder()
				.appendValue(DAY_OF_MONTH, 2)
				.appendLiteral('.')
				.appendValue(MONTH_OF_YEAR, 2)
				.appendLiteral('.')
				.appendValue(YEAR, 4)
				.toFormatter(Locale.GERMANY));
	}

}
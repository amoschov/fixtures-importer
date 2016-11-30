package de.augsburg1871.fixtures.flow.delta;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.annotation.Transformer;

import com.google.common.collect.Lists;

import de.augsburg1871.fixtures.persistence.model.Game;
import de.augsburg1871.fixtures.persistence.model.Referee;
import de.augsburg1871.fixtures.persistence.model.Result;

public class CSVRecordToGameTransformer {

	private final String season;

	public CSVRecordToGameTransformer(final String season) {
		this.season = season;
	}

	@Transformer
	public Game transform(final CSVRecord record) {

		final LocalDate date = parseDate(record.get("Datum"));
		final LocalTime time = parseTime(record.get("Zeit"));
		final Result result = Result.builder().result(extractResult(record.get("Ergebnis"))).build();
		final Result resultHalfTime = Result.builder().result(extractResult(record.get("Halbzeitergebnis"))).build();

		final List<Referee> referees = mapReferees(
				Lists.<String[]>newArrayList(
						new String[] { record.get("SR A"), record.get(" Verein SR A") },
						new String[] { record.get(" SR B"), record.get(" Verein SR B") },
						new String[] { record.get(" SR C"), record.get(" Verein SR C") },
						new String[] { record.get(" SR D"), record.get(" Verein SR D") }));

		final Game game = Game.builder()
				.localDateTime(LocalDateTime.of(date, time))
				.gameNumber(record.get("Spielnummer"))
				.gymNumber(record.get("Hallennummer"))
				.season(season)
				.home(record.get("Heimmannschaft"))
				.away(record.get("Gastmannschaft"))
				.result(result)
				.resultHalfTime(resultHalfTime)
				.referees(referees)
				.build();
		return game;
	}

	private List<Referee> mapReferees(final List<String[]> refs) {
		final List<Referee> referees = Lists.newArrayList();
		for (final String[] data : refs) {
			if (StringUtils.isBlank(data[0])) {
				continue;
			}
			referees.add(Referee.builder().name(data[0]).club(data[1]).build());
		}
		return referees;
	}

	private String extractResult(final String result) {
		return result.substring(2, result.length() - 1);
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
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.integration.annotation.Transformer;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import de.augsburg1871.fixtures.flow.LeagueMappings;
import de.augsburg1871.fixtures.persistence.model.Club;
import de.augsburg1871.fixtures.persistence.model.Game;
import de.augsburg1871.fixtures.persistence.model.Game.GameBuilder;
import de.augsburg1871.fixtures.persistence.model.LeagueMapping;
import de.augsburg1871.fixtures.persistence.model.Referee;
import de.augsburg1871.fixtures.persistence.model.Result;

public class CSVRecordToGameTransformer {

	private final String season;
	private final ClubDetector clubDetector;

	public CSVRecordToGameTransformer(final List<Club> clubs, final String season) {
		this.clubDetector = new ClubDetector(clubs);
		this.season = season;
	}

	@Transformer
	public Game transform(final CSVRecord record) {
		final LocalDate date = parseDate(record.get("Datum"));
		final LocalTime time = parseTime(record.get("Zeit"));
		final String home = record.get("Heimmannschaft");
		final String away = record.get("Gastmannschaft");
		final String staffel = record.get("Staffelkurzbezeichnung");
		final String liga = record.get("Liga");

		final LeagueMapping mapping = LeagueMappings.findMapping(liga, staffel);
		final Club homeClub = clubDetector.findClub(home);
		final Club awayClub = clubDetector.findClub(away);

		final Result result = Result.builder().result(extractResult(record.get("Ergebnis"))).build();
		final Result resultHalfTime = Result.builder().result(extractResult(record.get("Halbzeitergebnis"))).build();

		final List<Referee> referees = mapReferees(
				Lists.<String[]>newArrayList(
						new String[] { record.get("SR A"), record.get(" Verein SR A") },
						new String[] { record.get(" SR B"), record.get(" Verein SR B") },
						new String[] { record.get(" SR C"), record.get(" Verein SR C") },
						new String[] { record.get(" SR D"), record.get(" Verein SR D") }));

		final Game game = GameBuilder.with(LocalDateTime.of(date, time), home, away)
				.gameNumber(record.get("Spielnummer"))
				.gymNumber(record.get("Hallennummer"))
				.homeClub(homeClub)
				.awayClub(awayClub)
				.season(season)
				.team(mapping == null ? null : mapping.getTeam())
				.classOfAge(mapping == null ? null : mapping.getTeam().getClassOfAge())
				.sex(mapping == null ? null : mapping.getTeam().getSex())
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
		return CollectionUtils.isEmpty(referees) ? null : referees;
	}

	private String extractResult(final String result) {
		String adjusted = null;
		final Matcher matcher = Pattern.compile("\\d+:\\d+").matcher(result);
		if (matcher.find()) {
			adjusted = matcher.group();
		}
		if ("0:0".equals(adjusted)) {
			return null;
		}

		return adjusted;
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
package de.augsburg1871.fixtures.flow.delta;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;

import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import de.augsburg1871.fixtures.flow.CSVReader;
import de.augsburg1871.fixtures.persistence.model.Game;
import de.augsburg1871.fixtures.persistence.model.Referee;
import de.augsburg1871.fixtures.persistence.model.Result;

public class CSVRecordToGameTransformerTest {

	private final Resource resource = new ClassPathResource("regionsspielplan.csv");
	private Collection<CSVRecord> csvRecords;

	@Before
	public void readFile() throws IOException {
		csvRecords = new CSVReader().read(resource.getFile());
	}

	@Test
	public void read() {
		assertThat(csvRecords, is(iterableWithSize(1)));
	}

	@Test
	public void transform() {
		final Game game = new CSVRecordToGameTransformer("2016/17").transform(csvRecords.iterator().next());
		assertThat(game, is(notNullValue()));
		assertThat(game.getDate(), is(equalTo(LocalDateTime.of(2016, Month.OCTOBER, 9, 13, 00))));
		assertThat(game.getGymNumber(), is(equalTo("250004")));
		assertThat(game.getGameNumber(), is(equalTo("20043807")));
		assertThat(game.getSeason(), is(equalTo("2016/17")));
		assertThat(game.getHome(), is(equalTo("Augsburg 1871")));
		assertThat(game.getAway(), is(equalTo("TV Gundelfingen")));
		assertThat(game.getResultHalfTime(), is(equalTo(Result.builder().result("7:10").build())));
		assertThat(game.getResult(), is(equalTo(Result.builder().result(20, 19).build())));
		assertThat(game.getReferees(), contains(
				Referee.builder().name("Kubasta Wilhelm").club("TSG Augsburg").build(),
				Referee.builder().name("aName").club("aClub").build()));
	}

}

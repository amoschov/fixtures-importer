package de.augsburg1871.fixtures.flow.delta;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.collect.Lists;

import de.augsburg1871.fixtures.persistence.model.Club;
import de.augsburg1871.fixtures.persistence.model.Club.ClubBuilder;

public class ClubDetectorTest {

	@Test
	public void exactMatch() {
		final Club club = ClubBuilder.ofName("TSV 1871 Augsburg").build();
		final Club foundClub = new ClubDetector(Lists.newArrayList(club)).findClub("TSV 1871 Augsburg");

		assertThat(foundClub, is(sameInstance(club)));
		assertThat(foundClub.getName(), is(equalTo("TSV 1871 Augsburg")));
	}

	@Test
	public void matchAliasStartsWithClubName() {
		final Club club = ClubBuilder.ofName("TSV 1871 Augsburg").build();
		final Club foundClub = new ClubDetector(Lists.newArrayList(club)).findClub("TSV 1871 Augsburg II");

		assertThat(foundClub, is(sameInstance(club)));
		assertThat(foundClub.getName(), is(equalTo("TSV 1871 Augsburg")));
	}

	@Test
	public void mappingStartsWithAlias() {
		final Club club = ClubBuilder.ofName("TSV 1871 Augsburg").nuLigaMappings("Augsburg 1871").build();

		final ClubDetector detector = new ClubDetector(Lists.newArrayList(club));
		final Club foundClub = detector.findClub("Augsburg 1871");
		assertThat(foundClub, is(sameInstance(club)));
		assertThat(foundClub.getName(), is(equalTo("TSV 1871 Augsburg")));
	}

	@Test
	public void mappingContainsAlias() {
		final Club club = ClubBuilder.ofName("TSV 1871 Augsburg").nuLigaMappings("Augsburg 1871").build();

		final ClubDetector detector = new ClubDetector(Lists.newArrayList(club));
		final Club foundClub = detector.findClub("1871");
		assertThat(foundClub, is(sameInstance(club)));
		assertThat(foundClub.getName(), is(equalTo("TSV 1871 Augsburg")));
	}

	@Test
	public void aliasContainsMapping() {
		final Club club = ClubBuilder.ofName("TSV 1871 Augsburg").nuLigaMappings("Augsburg 1871").build();

		final ClubDetector detector = new ClubDetector(Lists.newArrayList(club));
		final Club foundClub = detector.findClub("Augsburg 1871 II");
		assertThat(foundClub, is(sameInstance(club)));
		assertThat(foundClub.getName(), is(equalTo("TSV 1871 Augsburg")));
	}

	public static void main(final String[] args) {
		System.out.println("Augsburg 1871 II".contains("Augsburg 1871"));
	}

	@Test
	public void nullIfNoMatch() {
		final Club club = ClubBuilder.ofName("TSV 1871 Augsburg").nuLigaMappings("Augsburg 1871").build();

		final ClubDetector detector = new ClubDetector(Lists.newArrayList(club));
		final Club foundClub = detector.findClub("TSG Augsburg");
		assertThat(foundClub, is(nullValue()));
	}
}

package de.augsburg1871.fixtures;

import java.util.List;

import org.springframework.integration.annotation.Filter;

import de.augsburg1871.fixtures.persistence.model.Fixture;

public class CsvLineFilter {

	private final List<String> teamsToImport;

	public CsvLineFilter(final List<String> teamsToImport) {
		this.teamsToImport = teamsToImport;
	}

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
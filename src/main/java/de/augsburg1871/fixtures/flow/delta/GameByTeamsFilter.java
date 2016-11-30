package de.augsburg1871.fixtures.flow.delta;

import java.util.List;

import org.springframework.integration.annotation.Filter;

import de.augsburg1871.fixtures.persistence.model.Game;

public class GameByTeamsFilter {

	private final List<String> teamsToImport;

	public GameByTeamsFilter(final List<String> teamsToImport) {
		this.teamsToImport = teamsToImport;
	}

	@Filter
	public boolean isValid(final Game game) {

		if (teamsToImport.contains(game.getHome())) {
			return true;
		} else if (teamsToImport.contains(game.getAway())) {
			return true;
		}

		return false;
	}

}
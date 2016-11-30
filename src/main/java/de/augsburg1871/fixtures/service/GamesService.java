package de.augsburg1871.fixtures.service;

import java.util.Collection;

import de.augsburg1871.fixtures.persistence.model.Game;

public interface GamesService {

	Collection<Game> findByGameNumber(String gameNumber);

	Collection<Game> findByGameNumberAndSeason(String gameNumber);

}

package de.augsburg1871.fixtures.service;

import java.util.Collection;
import java.util.List;

import de.augsburg1871.fixtures.persistence.model.Game;

public interface GamesService {

	Collection<Game> findByGameNumber(String gameNumber);

	Collection<Game> findByGameNumberAndSeason(String gameNumber, String season);

	Collection<Game> findByGameNumberAndSeasonAndGymNumber(String gameNumber, String season, String gymNumber);

	List<Game> save(Iterable<Game> games);

	Game save(Game game);

}

package de.augsburg1871.fixtures.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import de.augsburg1871.fixtures.persistence.model.Game;
import de.augsburg1871.fixtures.repository.GameRepository;

@Service
public class GamesServiceImpl implements GamesService {

	private final GameRepository gameRepository;

	public GamesServiceImpl(final GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	@Override
	public Collection<Game> findByGameNumber(final String gameNumber) {
		return gameRepository.findByGameNumber(gameNumber);
	}

	@Override
	public Collection<Game> findByGameNumberAndSeason(final String gameNumber, final String season) {
		return gameRepository.findByGameNumberAndSeason(gameNumber, season);
	}

	@Override
	public Collection<Game> findByGameNumberAndSeasonAndGymNumber(final String gameNumber, final String season,
			final String gymNumber) {
		return gameRepository.findByGameNumberAndSeasonAndGymNumber(gameNumber, season, gymNumber);
	}

	@Override
	public Game save(final Game game) {
		return gameRepository.save(game);
	}

	@Override
	public List<Game> save(final Iterable<Game> games) {
		return gameRepository.save(games);
	}

}

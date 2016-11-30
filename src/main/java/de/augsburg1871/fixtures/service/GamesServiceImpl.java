package de.augsburg1871.fixtures.service;

import java.util.Collection;

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
	public Collection<Game> findByGameNumberAndSeason(final String gameNumber) {
		return null;
	}

}

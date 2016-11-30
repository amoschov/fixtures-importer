package de.augsburg1871.fixtures.flow.delta;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.util.CollectionUtils;

import de.augsburg1871.fixtures.persistence.model.Game;
import de.augsburg1871.fixtures.persistence.model.GameMerger;
import de.augsburg1871.fixtures.repository.GameRepository;

public class UpsertHandler {

	Log log = LogFactory.getLog(UpsertHandler.class);

	private final GameRepository gameRepository;
	private final String currentSeason;

	public UpsertHandler(final GameRepository gameRepository, final String currentSeason) {
		this.gameRepository = gameRepository;
		this.currentSeason = currentSeason;
	}

	@ServiceActivator
	public void upsert(final Game game) {
		final Collection<Game> persistentGames = gameRepository
				.findByGameNumberAndSeasonAndGymNumber(game.getGameNumber(), currentSeason,
						game.getGymNumber());

		if (CollectionUtils.isEmpty(persistentGames)) {
			gameRepository.save(game);
			return;
		}

		if (persistentGames.size() == 1) {
			gameRepository.save(GameMerger.merge(persistentGames.iterator().next(), game));
			return;
		}

		log.error("Aktualisierung nicht möglich. Spiel mit Nummer [" + game.getGameNumber() + "] ist nicht eindeutig!");
	}

}

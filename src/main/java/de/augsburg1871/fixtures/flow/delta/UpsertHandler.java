package de.augsburg1871.fixtures.flow.delta;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.util.CollectionUtils;

import de.augsburg1871.fixtures.persistence.model.Game;
import de.augsburg1871.fixtures.service.GamesService;

public class UpsertHandler {

	private final Log log = LogFactory.getLog(UpsertHandler.class);

	private final GamesService gameService;
	private final String currentSeason;

	public UpsertHandler(final GamesService gameService, final String currentSeason) {
		this.gameService = gameService;
		this.currentSeason = currentSeason;
	}

	@ServiceActivator
	public void upsert(final Game game) {
		final Collection<Game> persistentGames = gameService
				.findByGameNumberAndSeasonAndGymNumber(game.getGameNumber(), currentSeason, game.getGymNumber());

		if (CollectionUtils.isEmpty(persistentGames)) {
			gameService.save(game);
			log.info("INSERT " + game);
			return;
		}

		if (persistentGames.size() == 1) {
			// gameService.save(GameMerger.merge(persistentGames.iterator().next(),
			// game));
			game.setId(persistentGames.iterator().next().getId());
			gameService.save(game);
			log.info("UPDATE " + game);
			return;
		}

		log.error("Aktualisierung nicht möglich. Spiel mit Nummer [" + game.getGameNumber() + "] ist nicht eindeutig!");
	}

}

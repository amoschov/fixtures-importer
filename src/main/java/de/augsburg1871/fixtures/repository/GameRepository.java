package de.augsburg1871.fixtures.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.augsburg1871.fixtures.persistence.model.Game;

public interface GameRepository extends MongoRepository<Game, Long> {

	Collection<Game> findByGameNumber(String gameNumber);

	Collection<Game> findByGameNumberAndSeason(String gameNumber, String season);

	Collection<Game> findByGameNumberAndSeasonAndGymNumber(String gameNumber, String season, String gymNumber);

}

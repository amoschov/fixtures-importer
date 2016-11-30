package de.augsburg1871.fixtures.repository;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

import de.augsburg1871.fixtures.persistence.model.Game;

public interface GameRepository extends PagingAndSortingRepository<Game, Long> {

	Collection<Game> findByGameNumber(String gameNumber);

	Collection<Game> findByGameNumberAndSeasonAndGymNumber(String gameNumber, String season, String gymNumber);

}

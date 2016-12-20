package de.augsburg1871.fixtures.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.augsburg1871.fixtures.persistence.model.Club;

public interface ClubRepository extends MongoRepository<Club, String> {

	List<Club> findByName(String name);

	List<Club> findByNameAndNuLigaNr(String name, String nuLigaNr);

	List<Club> findByNuLigaMappingsContains(String name);

}

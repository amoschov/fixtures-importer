package de.augsburg1871.fixtures.service;

import java.util.List;

import de.augsburg1871.fixtures.persistence.model.Club;

public interface ClubService {

	Club save(Club club);

	List<Club> save(Iterable<Club> clubs);

	List<Club> findAll();

	List<Club> findAllByName(String name);

	List<Club> findAllByNameAndNuLigaNr(String name, String nuLigaNr);

	List<Club> findAllWithAlias(String name);

}

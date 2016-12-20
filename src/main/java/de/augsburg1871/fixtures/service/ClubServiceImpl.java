package de.augsburg1871.fixtures.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.augsburg1871.fixtures.persistence.model.Club;
import de.augsburg1871.fixtures.repository.ClubRepository;

@Service
public class ClubServiceImpl implements ClubService {

	private final ClubRepository repository;

	public ClubServiceImpl(final ClubRepository clubRepository) {
		this.repository = clubRepository;
	}

	@Override
	public Club save(final Club club) {
		return repository.save(club);
	}

	@Override
	public List<Club> save(final Iterable<Club> clubs) {
		return repository.save(clubs);
	}

	@Override
	public List<Club> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Club> findAllByName(final String name) {
		return repository.findByName(name);
	}

	@Override
	public List<Club> findAllByNameAndNuLigaNr(final String name, final String nuLigaNr) {
		return repository.findByNameAndNuLigaNr(name, nuLigaNr);
	}

	@Override
	public List<Club> findAllWithAlias(final String name) {
		return repository.findByNuLigaMappingsContains(name);
	}

}

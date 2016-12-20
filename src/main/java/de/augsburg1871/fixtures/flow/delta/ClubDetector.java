package de.augsburg1871.fixtures.flow.delta;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import de.augsburg1871.fixtures.persistence.model.Club;

@Component
public class ClubDetector {

	// private final ClubService clubService;
	private final List<Club> clubs;

	public ClubDetector(final List<Club> clubs) {
		this.clubs = clubs;
	}

	// public ClubDetector(final ClubService clubService) {
	// this.clubService = clubService;
	// }
	//
	// @PostConstruct
	// public void loadClubs() {
	// clubs = clubService.findAll();
	// }

	public Club findClub(final String alias) {
		// exact match per name?
		Optional<Club> club = clubs.stream().filter(c -> alias.equals(c.getName())).findFirst();
		if (club.isPresent()) {
			return club.get();
		}

		// match in mappings?
		club = clubs.stream().filter(c -> {
			for (final String mapping : c.getNuLigaMappings()) {
				if (mapping.startsWith(alias) || mapping.contains(alias)) {
					return true;
				}
			}
			return false;
		}).findFirst();
		if (club.isPresent()) {
			return club.get();
		}

		return null;
	}

}

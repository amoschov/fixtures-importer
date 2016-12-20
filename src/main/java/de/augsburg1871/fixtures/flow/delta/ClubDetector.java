package de.augsburg1871.fixtures.flow.delta;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import de.augsburg1871.fixtures.persistence.model.Club;

public class ClubDetector {

	// private final ClubService clubService;
	private final List<Club> clubs;
	private final Log log = LogFactory.getLog(ClubDetector.class);

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
		if (StringUtils.isBlank(alias)) {
			return null;
		}

		final String aliasClubName = alias.trim();

		// exact match per name?
		Optional<Club> club = clubs.stream()
				.filter(c -> (aliasClubName.equals(c.getName()) || aliasClubName.startsWith(c.getName()))).findFirst();
		if (club.isPresent()) {
			return club.get();
		}

		// match in mappings?
		club = clubs.stream().filter(c -> {
			final List<String> nuLigaMappings = c.getNuLigaMappings();
			if (CollectionUtils.isEmpty(nuLigaMappings)) {
				return false;
			}
			for (final String mapping : nuLigaMappings) {
				if (mapping.startsWith(aliasClubName)
						|| mapping.contains(aliasClubName)
						|| aliasClubName.contains(mapping)) {
					return true;
				}
			}
			return false;
		}).findFirst();
		if (club.isPresent()) {
			return club.get();
		}

		log.error("Couldn't find Club for " + aliasClubName);
		return null;
	}

}

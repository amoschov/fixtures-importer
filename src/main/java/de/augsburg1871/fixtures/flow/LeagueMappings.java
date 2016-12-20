package de.augsburg1871.fixtures.flow;

import static de.augsburg1871.fixtures.persistence.model.LeagueMapping.LeagueMappingBuilder.map;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

import de.augsburg1871.fixtures.persistence.model.LeagueMapping;
import de.augsburg1871.fixtures.persistence.model.Team;

public class LeagueMappings {

	private static Log log = LogFactory.getLog(LeagueMappings.class);

	public static List<LeagueMapping> getMappingsForDeltaFlow() {
		return Lists.newArrayList(
				map("BZOL M", "Bezirksoberliga M").onto(Team.M),
				map("BZK M", "Bezirksklasse Staffel West").onto(Team.M2),
				map("BZL F", "Bezirksliga F").onto(Team.F),
				map("ÜBL MB", "ÜBL Staffel SW 1").onto(Team.mB),
				map("ÜBL MC", "ÜBL Staffel SW 1").onto(Team.mC),
				map("BZL MD", "Bezirksliga").onto(Team.mD));
	}

	public static LeagueMapping findMapping(final String league, final String relay) {
		if (StringUtils.isEmpty(league) || StringUtils.isEmpty(relay)) {
			return null;
		}

		final String leagueTrimmed = league.trim();
		final String relayTrimmed = relay.trim();

		final List<LeagueMapping> validMappings = getMappingsForDeltaFlow().stream().filter(m -> {
			final LeagueMapping mapping = m;
			if (!leagueTrimmed.equals(mapping.getNuLigaLeague())) {
				return false;
			}
			if (!relayTrimmed.equals(mapping.getNuLigaRelay())) {
				return false;
			}
			return true;
		}).collect(Collectors.toList());

		if (CollectionUtils.isEmpty(validMappings)) {
			log.error("No Mapping for " + league + " " + relay);
			return null;
		}

		if (validMappings.size() > 1) {
			log.error("Mapping is not unique! " + validMappings);
			return null;
		}

		return validMappings.get(0);
	}

}

package de.augsburg1871.fixtures.flow.delta;

import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.springframework.integration.annotation.Filter;

import de.augsburg1871.fixtures.flow.LeagueMappings;
import de.augsburg1871.fixtures.persistence.model.LeagueMapping;

public class LeagueRelayFilter {

	static List<LeagueMapping> mappings = LeagueMappings.getMappingsForDeltaFlow();

	@Filter
	public boolean filter(final CSVRecord record) {
		final String liga = record.get("Liga");
		final String staffel = record.get("Staffelkurzbezeichnung");

		final LeagueMapping mapping = LeagueMappings.findMapping(liga, staffel);
		return mapping != null;
	}

}

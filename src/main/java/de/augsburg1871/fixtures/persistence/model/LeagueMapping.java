package de.augsburg1871.fixtures.persistence.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LeagueMapping {

	private Team team;
	private String nuLigaLeague;
	private String nuLigaRelay;

	public Team getTeam() {
		return team;
	}

	public void setTeam(final Team team) {
		this.team = team;
	}

	public String getNuLigaLeague() {
		return nuLigaLeague;
	}

	public void setNuLigaLeague(final String nuLigaLeague) {
		this.nuLigaLeague = nuLigaLeague;
	}

	public String getNuLigaRelay() {
		return nuLigaRelay;
	}

	public void setNuLigaRelay(final String nuLigaRelay) {
		this.nuLigaRelay = nuLigaRelay;
	}

	public static class LeagueMappingBuilder {

		private Team team;
		private String nuLigaLeague;
		private String nuLigaRelay;

		public LeagueMappingBuilder team(final Team team) {
			this.team = team;
			return this;
		}

		public LeagueMappingBuilder nuLigaLeague(final String nuLigaLeague) {
			this.nuLigaLeague = nuLigaLeague;
			return this;
		}

		public LeagueMappingBuilder nuLigaRelay(final String nuLigaRelay) {
			this.nuLigaRelay = nuLigaRelay;
			return this;
		}

		public LeagueMapping build() {
			final LeagueMapping leagueMapping = new LeagueMapping();
			leagueMapping.setTeam(this.team);
			leagueMapping.setNuLigaLeague(this.nuLigaLeague);
			leagueMapping.setNuLigaRelay(this.nuLigaRelay);

			return leagueMapping;
		}

		public static LeagueMappingBuilder map(final String league, final String relay) {
			return new LeagueMappingBuilder().nuLigaLeague(league).nuLigaRelay(relay);
		}

		public LeagueMapping onto(final Team team) {
			return this.team(team).build();
		}
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof LeagueMapping)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		final LeagueMapping rhs = (LeagueMapping) obj;
		return new EqualsBuilder()
				.append(team, rhs.getTeam())
				.append(nuLigaLeague, rhs.getNuLigaLeague())
				.append(nuLigaRelay, rhs.getNuLigaRelay())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(team).append(nuLigaLeague).append(nuLigaRelay).build();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(nuLigaLeague).append(nuLigaRelay).append(team).build();
	}

}

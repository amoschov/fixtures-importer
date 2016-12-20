package de.augsburg1871.fixtures.persistence.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.core.style.ToStringCreator;

public class Referee {

	private String name;
	private String club;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getClub() {
		return club;
	}

	public void setClub(final String club) {
		this.club = club;
	}

	public static RefereeBuilder builder() {
		return new RefereeBuilder();
	}

	@Override
	public String toString() {
		return new ToStringCreator(this).append(name).toString();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		final Referee rhs = (Referee) obj;
		return new EqualsBuilder()
				.append(name, rhs.getName())
				.append(club, rhs.getClub())
				.isEquals();
	}

	public static class RefereeBuilder {

		private String name;
		private String club;

		public RefereeBuilder name(final String name) {
			this.name = name;
			return this;
		}

		public RefereeBuilder club(final String club) {
			this.club = club;
			return this;
		}

		public Referee build() {
			if (StringUtils.isEmpty(name)) {
				return null;
			}

			final Referee referee = new Referee();
			referee.setName(this.name);
			referee.setClub(this.club);

			return referee;
		}

	}
}
package de.augsburg1871.fixtures.persistence.model;

import java.time.LocalDateTime;

import org.springframework.core.style.ToStringCreator;

public class Fixture {

	private LocalDateTime dateTime;
	private String home;
	private String away;

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(final LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getHome() {
		return home;
	}

	public void setHome(final String home) {
		this.home = home;
	}

	public String getAway() {
		return away;
	}

	public void setAway(final String away) {
		this.away = away;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this).append(dateTime).append(home).append(away).toString();
	}

	public static class FixtureBuilder {
		private LocalDateTime dateTime;
		private String home;
		private String away;

		public FixtureBuilder localDateTime(final LocalDateTime localDateTime) {
			this.dateTime = localDateTime;
			return this;
		}

		public FixtureBuilder away(final String away) {
			this.away = away;
			return this;
		}

		public FixtureBuilder home(final String home) {
			this.home = home;
			return this;
		}

		public Fixture build() {
			final Fixture fixture = new Fixture();
			fixture.setHome(home);
			fixture.setAway(away);
			fixture.setDateTime(dateTime);
			return fixture;
		}

	}
}

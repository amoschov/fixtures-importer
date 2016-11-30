package de.augsburg1871.fixtures.persistence.model;

public class Referee {

	private String name;
	private String club;

	private Referee() {

	}

	// public Referee(final String name) {
	// this.name = name;
	// }
	//
	// public Referee(final String name, final String club) {
	// this.name = name;
	// this.club = club;
	// }

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
			final Referee referee = new Referee();
			referee.setName(this.name);
			referee.setClub(this.club);

			return referee;
		}

	}
}
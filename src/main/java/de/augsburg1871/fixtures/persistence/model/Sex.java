package de.augsburg1871.fixtures.persistence.model;

public enum Sex {

	MALE("männliche"), FEMALE("weibliche"), MIXED("gemischte");

	private String description;

	private Sex(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}

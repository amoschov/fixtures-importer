package de.augsburg1871.fixtures.persistence.model;

public class GameMerger {

	public static Game merge(final Game persistent, final Game read) {

		persistent.setLocalDateTime(read.getLocalDateTime());
		persistent.setResult(read.getResult());
		persistent.setResultHalfTime(read.getResultHalfTime());
		persistent.setReferees(read.getReferees());

		return persistent;
	}
}

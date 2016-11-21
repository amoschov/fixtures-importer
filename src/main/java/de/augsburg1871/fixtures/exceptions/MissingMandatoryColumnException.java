package de.augsburg1871.fixtures.exceptions;

public class MissingMandatoryColumnException extends RuntimeException {

	private static final long serialVersionUID = 9137286626362984263L;
	private final String columnName;

	public MissingMandatoryColumnException(final String columnName) {
		this.columnName = columnName;
	}

	@Override
	public String getMessage() {
		return "Spalte '" + columnName + "' konnte nicht gefunden werden.";
	}

}

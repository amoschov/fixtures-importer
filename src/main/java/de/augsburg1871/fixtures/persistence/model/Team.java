package de.augsburg1871.fixtures.persistence.model;

import static de.augsburg1871.fixtures.persistence.model.ClassOfAge.B;
import static de.augsburg1871.fixtures.persistence.model.ClassOfAge.C;
import static de.augsburg1871.fixtures.persistence.model.ClassOfAge.D;
import static de.augsburg1871.fixtures.persistence.model.ClassOfAge.E;
import static de.augsburg1871.fixtures.persistence.model.ClassOfAge.MEN;
import static de.augsburg1871.fixtures.persistence.model.ClassOfAge.MINIS;
import static de.augsburg1871.fixtures.persistence.model.ClassOfAge.WOMEN;
import static de.augsburg1871.fixtures.persistence.model.Sex.FEMALE;
import static de.augsburg1871.fixtures.persistence.model.Sex.MALE;
import static de.augsburg1871.fixtures.persistence.model.Sex.MIXED;

public enum Team {

	M("Männer", MALE, MEN),
	M2("Männer II", MALE, MEN),
	F("Frauen", FEMALE, WOMEN),
	mB("männliche B-Jugend", MALE, B),
	mC("männliche C-Jugend", MALE, C),
	mD("männliche D-Jugend", MALE, D),
	gemE("gemischte E-Jugend", MIXED, E),
	Minis("Minis", MIXED, MINIS);

	private final String name;
	private final Sex sex;
	private final ClassOfAge classOfAge;

	private Team(final String name, final Sex sex, final ClassOfAge classOfAge) {
		this.name = name;
		this.sex = sex;
		this.classOfAge = classOfAge;
	}

	public String getName() {
		return name;
	}

	public Sex getSex() {
		return sex;
	}

	public ClassOfAge getClassOfAge() {
		return classOfAge;
	}

}

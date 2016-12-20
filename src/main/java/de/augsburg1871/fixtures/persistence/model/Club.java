package de.augsburg1871.fixtures.persistence.model;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.collect.Lists;

@Document(collection = "clubs")
public class Club {

	private String id;
	private String name;
	private String nuLigaNr;
	private List<String> nuLigaMappings;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getNuLigaNr() {
		return nuLigaNr;
	}

	public void setNuLigaNr(final String nuLigaNr) {
		this.nuLigaNr = nuLigaNr;
	}

	public List<String> getNuLigaMappings() {
		return nuLigaMappings;
	}

	public void setNuLigaMappings(final List<String> nuLigaMappings) {
		this.nuLigaMappings = nuLigaMappings;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Club)) {
			return false;
		}
		if (obj == this) {
			return true;
		}

		final Club rhs = (Club) obj;
		return new EqualsBuilder()
				.append(name, rhs.name)
				.append(name, rhs.name)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nuLigaNr).append(name).build();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(nuLigaNr).append(name).build();
	}

	public static class ClubBuilder {

		private String id;
		private String name;
		private String nuLigaNr;
		private final List<String> nuLigaMappings = Lists.newArrayList();

		public ClubBuilder id(final String id) {
			this.id = id;
			return this;
		}

		public ClubBuilder name(final String name) {
			this.name = name;
			return this;
		}

		public ClubBuilder nuLigaNr(final String nuLigaNr) {
			this.nuLigaNr = nuLigaNr;
			return this;
		}

		public ClubBuilder nuLigaMappings(final String... nuLigaMappings) {
			this.nuLigaMappings.addAll(Arrays.asList(nuLigaMappings));
			return this;
		}

		public Club build() {
			final Club club = new Club();
			club.setId(this.id);
			club.setName(this.name);
			club.setNuLigaMappings(this.nuLigaMappings);
			club.setNuLigaNr(this.nuLigaNr);

			return club;
		}
	}

}

package de.augsburg1871.fixtures.persistence.model;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.springframework.core.style.ToStringCreator;

public class Result {

	private Integer home;
	private Integer away;

	public Integer getHome() {
		return home;
	}

	public void setHome(final Integer home) {
		this.home = home;
	}

	public Integer getAway() {
		return away;
	}

	public void setAway(final Integer away) {
		this.away = away;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append(home)
				.append(away)
				.toString();
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
		final Result rhs = (Result) obj;
		return new EqualsBuilder()
				.append(home, rhs.getHome())
				.append(away, rhs.getAway())
				.isEquals();
	}

	public static ResultBuilder builder() {
		return new ResultBuilder();
	}

	public static class ResultBuilder {

		private Integer home;
		private Integer away;

		public ResultBuilder home(final Integer home) {
			checkNotNull(home);
			this.home = home;
			return this;
		}

		public ResultBuilder away(final Integer away) {
			checkNotNull(away);
			this.away = away;
			return this;
		}

		public ResultBuilder result(final Integer home, final Integer away) {
			return home(home).away(away);
		}

		public ResultBuilder result(final String result) {
			return result(result, ':');
		}

		public ResultBuilder result(final String result, final char separator) {
			if (StringUtils.isEmpty(result)) {
				return this;
			}

			final String[] results = result.split(String.valueOf(separator));
			final Integer home = Integer.valueOf(results[0]);
			final Integer away = Integer.valueOf(results[1]);
			return home(home).away(away);
		}

		public Result build() {
			if (home == null || away == null) {
				return null;
			}

			final Result result = new Result();
			result.setHome(home);
			result.setAway(away);
			return result;
		}

	}
}

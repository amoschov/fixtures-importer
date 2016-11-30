package de.augsburg1871.fixtures.persistence.model;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.core.style.ToStringCreator;

public class Result {

	private Integer home;
	private Integer away;

	private Result(final Integer home, final Integer away) {
		this.home = home;
		this.away = away;
	}

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

		public Result build() {
			final Result result = new Result(home, away);
			return result;
		}

	}
}

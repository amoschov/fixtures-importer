package de.augsburg1871.fixtures.persistence.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.style.ToStringCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "games")
public class Game {

	@Id
	private String id;

	@Field(value = "date")
	private LocalDateTime localDateTime;

	private String gameNumber;
	private String season;
	private String home;
	private String away;
	private String gymNumber;
	private Result result;
	private Result resultHalfTime;
	private List<Referee> referees;

	// private Game() {
	//
	// }

	// public long getId() {
	// return id;
	// }
	//
	// public void setId(final long id) {
	// this.id = id;
	// }

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(final LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
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

	public Result getResult() {
		return result;
	}

	public void setResult(final Result result) {
		this.result = result;
	}

	public Result getResultHalfTime() {
		return resultHalfTime;
	}

	public void setResultHalfTime(final Result resultHalfTime) {
		this.resultHalfTime = resultHalfTime;
	}

	public List<Referee> getReferees() {
		return referees;
	}

	public void setReferees(final List<Referee> referees) {
		this.referees = referees;
	}

	public String getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(final String gameNumber) {
		this.gameNumber = gameNumber;
	}

	public static GameBuilder builder() {
		return new GameBuilder();
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(final String season) {
		this.season = season;
	}

	public String getGymNumber() {
		return gymNumber;
	}

	public void setGymNumber(final String gymNumber) {
		this.gymNumber = gymNumber;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append(gameNumber)
				.append(localDateTime)
				.append(home)
				.append(away)
				.toString();
	}

	public static class GameBuilder {

		private LocalDateTime localDateTime;
		private String gameNumber;
		private String season;
		private String home;
		private String away;
		private String gymNumber;
		private List<Referee> referees;
		private Result result;
		private Result resultHalfTime;

		public GameBuilder localDateTime(final LocalDateTime localDateTime) {
			this.localDateTime = localDateTime;
			return this;
		}

		public GameBuilder gameNumber(final String gameNumber) {
			this.gameNumber = gameNumber;
			return this;
		}

		public GameBuilder season(final String season) {
			this.season = season;
			return this;
		}

		public GameBuilder home(final String home) {
			this.home = home;
			return this;

		}

		public GameBuilder away(final String away) {
			this.away = away;
			return this;

		}

		public GameBuilder result(final Result result) {
			this.result = result;
			return this;
		}

		public GameBuilder resultHalfTime(final Result result) {
			this.resultHalfTime = result;
			return this;
		}

		public GameBuilder referees(final List<Referee> referees) {
			this.referees = referees;
			return this;
		}

		public GameBuilder gymNumber(final String gymNumber) {
			this.gymNumber = gymNumber;
			return this;
		}

		public Game build() {
			checkNotNull(localDateTime);
			checkArgument(StringUtils.isNotBlank(home));
			checkArgument(StringUtils.isNotBlank(away));

			final Game fixture = new Game();
			fixture.setLocalDateTime(this.localDateTime);
			fixture.setSeason(this.season);
			fixture.setHome(this.home);
			fixture.setAway(this.away);
			fixture.setGameNumber(this.gameNumber);
			fixture.setGymNumber(this.gymNumber);
			fixture.setReferees(this.referees);
			fixture.setResult(this.result);
			fixture.setResultHalfTime(this.resultHalfTime);

			return fixture;
		}

	}

}

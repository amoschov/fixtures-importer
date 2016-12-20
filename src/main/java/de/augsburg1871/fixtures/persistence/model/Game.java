package de.augsburg1871.fixtures.persistence.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.style.ToStringCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "games")
public class Game {

	@Id
	private String id;
	private LocalDateTime date;
	private String gameNumber;
	@DBRef
	private Club homeClub;
	private String home;
	@DBRef
	private Club awayClub;
	private String away;
	private String gymNumber;

	private String season;
	private ClassOfAge classOfAge;
	private Sex sex;
	private Team team;

	private Result result;
	private Result resultHalfTime;

	private List<Referee> referees;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(final LocalDateTime date) {
		this.date = date;
	}

	public String getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(final String gameNumber) {
		this.gameNumber = gameNumber;
	}

	public String getHome() {
		return home;
	}

	public void setHome(final String home) {
		this.home = home;
	}

	public Club getHomeClub() {
		return homeClub;
	}

	public void setHomeClub(final Club homeClub) {
		this.homeClub = homeClub;
	}

	public String getAway() {
		return away;
	}

	public void setAway(final String away) {
		this.away = away;
	}

	public Club getAwayClub() {
		return awayClub;
	}

	public void setAwayClub(final Club awayClub) {
		this.awayClub = awayClub;
	}

	public String getGymNumber() {
		return gymNumber;
	}

	public void setGymNumber(final String gymNumber) {
		this.gymNumber = gymNumber;
	}

	public ClassOfAge getClassOfAge() {
		return classOfAge;
	}

	public void setClassOfAge(final ClassOfAge classOfAge) {
		this.classOfAge = classOfAge;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(final Sex sex) {
		this.sex = sex;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(final Team team) {
		this.team = team;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(final String season) {
		this.season = season;
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

	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append(gameNumber)
				.append(date)
				.append(home)
				.append(away)
				.toString();
	}

	public static class GameBuilder {

		private LocalDateTime localDateTime;
		private String gameNumber;
		private String season;
		private Club homeClub;
		private String home;
		private Club awayClub;
		private String away;
		private String gymNumber;
		private List<Referee> referees;
		private Result result;
		private Result resultHalfTime;
		private ClassOfAge classOfAge;
		private Sex sex;
		private Team team;

		public static GameBuilder with(final LocalDateTime date, final String home, final String away) {
			return new GameBuilder().localDateTime(date).home(home).away(away);
		}

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

		public GameBuilder homeClub(final Club homeClub) {
			this.homeClub = homeClub;
			return this;
		}

		public GameBuilder away(final String away) {
			this.away = away;
			return this;
		}

		public GameBuilder awayClub(final Club awayClub) {
			this.awayClub = awayClub;
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

		public GameBuilder classOfAge(final ClassOfAge classOfAge) {
			this.classOfAge = classOfAge;
			return this;
		}

		public GameBuilder sex(final Sex sex) {
			this.sex = sex;
			return this;
		}

		public GameBuilder team(final Team team) {
			this.team = team;
			return this;
		}

		public Game build() {
			checkNotNull(localDateTime);
			checkArgument(StringUtils.isNotBlank(home));
			checkArgument(StringUtils.isNotBlank(away));

			final Game game = new Game();
			game.setDate(this.localDateTime);
			game.setSeason(this.season);
			game.setClassOfAge(this.classOfAge);
			game.setSex(this.sex);
			game.setTeam(team);
			game.setHome(this.home);
			game.setHomeClub(this.homeClub);
			game.setAway(this.away);
			game.setAwayClub(this.awayClub);
			game.setGameNumber(this.gameNumber);
			game.setGymNumber(this.gymNumber);
			game.setReferees(this.referees);
			game.setResult(this.result);
			game.setResultHalfTime(this.resultHalfTime);

			return game;
		}

	}

}

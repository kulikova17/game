package net.thumbtack.footballgame.entities;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "games")

public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "score_team1")
    private int scoreTeam1;

    @Column(name = "score_team2")
    private int scoreTeam2;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @OneToOne
    @JoinColumn(name = "type_id", nullable = false)
    private GameType type;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private List<UserGame> players;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    @Where(clause = "team=1")
    private List<UserGame> team1;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    @Where(clause = "team=2")
    private List<UserGame> team2;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    @Where(clause = "team=3")
    private List<UserGame> missedPlayers;

    public Game() {
        this(null);
    }

    public Game(GameType type, int scoreTeam1, int scoreTeam2, GameStatus status,
                List<UserGame> players, List<UserGame> team1, List<UserGame> team2, List<UserGame> missedPlayers) {
        setScoreTeam1(scoreTeam1);
        setScoreTeam2(scoreTeam2);
        setType(type);
        setMissedPlayers(missedPlayers);
        setPlayers(players);
        setTeam1(team1);
        setTeam2(team2);
        setStatus(status);
    }

    public Game(int scoreTeam1, int scoreTeam2, GameType type, GameStatus status) {
        this(type, scoreTeam1, scoreTeam2, status, null, null, null, null);
    }

    public Game(GameType type) {
        this(0, 0, type, GameStatus.SCHEDULED);
    }


    public void addPlayer(UserGame player) {
        players.add(player);
    }

    public void removePlayer(UserGame player) {
        players.remove(player);
    }

    public void addPlayerTeam1(UserGame player) {
        team1.add(player);
    }

    public void removePlayerTeam1(UserGame player) {
        team1.remove(player);
    }

    public void addPlayerTeam2(UserGame player) {
        team2.add(player);
    }

    public void removePlayerTeam2(UserGame player) {
        team2.remove(player);
    }

    public void addMissedPlayer(UserGame player) {
        missedPlayers.add(player);
    }

    public void removeMissedPlayer(UserGame player) {
        missedPlayers.remove(player);
    }

    public int getId() {
        return id;
    }

    public void setId(int gameId) {
        this.id = gameId;
    }

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(int scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(int scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public List<UserGame> getPlayers() {
        return players;
    }

    public void setPlayers(List<UserGame> players) {
        this.players = players;
        if (players == null) {
            this.players = new ArrayList<>();
        }
    }

    public List<UserGame> getTeam1() {
        return team1;
    }

    public void setTeam1(List<UserGame> team1) {
        this.team1 = team1;
        if (team1 == null) {
            this.team1 = new ArrayList<>();
        }
    }

    public List<UserGame> getTeam2() {
        return team2;
    }

    public void setTeam2(List<UserGame> team2) {
        this.team2 = team2;
        if (team2 == null) {
            this.team2 = new ArrayList<>();
        }
    }

    public List<UserGame> getMissedPlayers() {
        return missedPlayers;
    }

    public void setMissedPlayers(List<UserGame> missedPlayers) {
        this.missedPlayers = missedPlayers;
        if (missedPlayers == null) {
            this.missedPlayers = new ArrayList<>();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return getId() == game.getId() &&
                getScoreTeam1() == game.getScoreTeam1() &&
                getScoreTeam2() == game.getScoreTeam2() &&
                Objects.equals(getType(), game.getType()) &&
                getStatus() == game.getStatus() &&
                Objects.equals(getPlayers(), game.getPlayers()) &&
                Objects.equals(getTeam1(), game.getTeam1()) &&
                Objects.equals(getTeam2(), game.getTeam2()) &&
                Objects.equals(getMissedPlayers(), game.getMissedPlayers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getScoreTeam1(), getScoreTeam2(), getStatus(),
                getPlayers(), getTeam1(), getTeam2(), getMissedPlayers());
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", type=" + type +
                ", scoreTeam1=" + scoreTeam1 +
                ", scoreTeam2=" + scoreTeam2 +
                ", gameStatus=" + status +
                ", players=" + players +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", missedPlayers=" + missedPlayers +
                '}';
    }
}

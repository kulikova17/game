package net.thumbtack.footballgame.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_game")
public class UserGame implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private int team;

    public UserGame(User user, Game game, int team) {
        setUser(user);
        setGame(game);
        setTeam(team);
    }

    public UserGame() {
    }

    public UserGame(User user, Game game) {
        this(user, game, 0);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGame)) return false;
        UserGame userGame = (UserGame) o;
        return getId() == userGame.getId() &&
                getTeam() == userGame.getTeam();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "UserGame{" +
                "id=" + id +
                ", team=" + team +
                '}';
    }
}

package net.thumbtack.footballgame.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "game_types")
public class GameType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    public GameType() {
    }

    public GameType(String type) {
        setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String gameType) {
        this.type = gameType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameType)) return false;
        GameType gameType1 = (GameType) o;
        return Objects.equals(getType(), gameType1.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType());
    }

    @Override
    public String toString() {
        return "GameType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}

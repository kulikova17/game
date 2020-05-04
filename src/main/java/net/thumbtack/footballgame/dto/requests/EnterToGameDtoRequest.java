package net.thumbtack.footballgame.dto.requests;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class EnterToGameDtoRequest {
    @NotNull
    private int gameId;

    public EnterToGameDtoRequest() {
    }

    public EnterToGameDtoRequest(int gameId) {
        setGameId(gameId);
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnterToGameDtoRequest)) return false;
        EnterToGameDtoRequest enter = (EnterToGameDtoRequest) o;
        return getGameId() == enter.getGameId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId());
    }

    @Override
    public String toString() {
        return "EnterToGameDtoRequest{" +
                "gameId=" + gameId +
                '}';
    }
}

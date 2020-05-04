package net.thumbtack.footballgame.dto.responses;

import java.util.Objects;

public class EnterToGameDtoResponse {
    private int gameId;
    private String gameType;
    private String gameStatus;

    public EnterToGameDtoResponse() {
    }

    public EnterToGameDtoResponse(int gameId, String gameType, String gameStatus) {
        setGameId(gameId);
        setGameStatus(gameStatus);
        setGameType(gameType);
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnterToGameDtoResponse)) return false;
        EnterToGameDtoResponse that = (EnterToGameDtoResponse) o;
        return getGameId() == that.getGameId() &&
                Objects.equals(getGameType(), that.getGameType()) &&
                Objects.equals(getGameStatus(), that.getGameStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getGameType(), getGameStatus());
    }

    @Override
    public String toString() {
        return "EnterToGameDtoResponse{" +
                "gameId=" + gameId +
                ", gameType='" + gameType + '\'' +
                ", gameStatus='" + gameStatus + '\'' +
                '}';
    }
}

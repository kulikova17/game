package net.thumbtack.footballgame.dto.responses;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UpdateGameStatisticDtoResponse {
    private int gameId;
    private Integer scoreTeam1;
    private Integer scoreTeam2;
    private String status;
    private Map<String, Integer> teams;

    public UpdateGameStatisticDtoResponse() {
    }

    public UpdateGameStatisticDtoResponse(int gameId, int scoreTeam1, int scoreTeam2, String status,
                                          Map<String, Integer> teams) {
        setGameId(gameId);
        setScoreTeam1(scoreTeam1);
        setScoreTeam2(scoreTeam2);
        setStatus(status);
        setTeams(teams);
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Map<String, Integer> getTeams() {
        return teams;
    }

    public Integer getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(Integer scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public Integer getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(Integer scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTeams(Map<String, Integer> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateGameStatisticDtoResponse)) return false;
        UpdateGameStatisticDtoResponse that = (UpdateGameStatisticDtoResponse) o;
        return getGameId() == that.getGameId() &&
                scoreTeam1 == that.scoreTeam1 &&
                scoreTeam2 == that.scoreTeam2 &&
                Objects.equals(status, that.status) &&
                Objects.equals(getTeams(), that.getTeams());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), scoreTeam1, scoreTeam2, status, getTeams());
    }

    @Override
    public String toString() {
        return "UpdateGameStatisticDtoRequest{" +
                "gameId=" + gameId +
                ", scoreTeam1=" + scoreTeam1 +
                ", scoreTeam2=" + scoreTeam2 +
                ", status='" + status + '\'' +
                ", teams=" + teams +
                '}';
    }
}

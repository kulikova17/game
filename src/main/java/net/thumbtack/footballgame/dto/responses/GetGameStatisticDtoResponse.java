package net.thumbtack.footballgame.dto.responses;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetGameStatisticDtoResponse {
    private int scoreTeam1;
    private int scoreTeam2;
    private String status;
    private Map<String, Integer> teams;

    public GetGameStatisticDtoResponse() {
        setTeams(null);
    }


    public GetGameStatisticDtoResponse(int scoreTeam1, int scoreTeam2, String status, Map<String, Integer> teams) {
        setScoreTeam1(scoreTeam1);
        setScoreTeam2(scoreTeam2);
        setStatus(status);
        setTeams(teams);
    }

    public GetGameStatisticDtoResponse(int scoreTeam1, int scoreTeam2, String status) {
        this(scoreTeam1, scoreTeam2, status, new HashMap<>());
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Integer> getTeams() {
        return teams;
    }

    public void setTeams(Map<String, Integer> teams) {
        this.teams = teams;
        if(teams == null) {
            this.teams = new HashMap<>();
        }
    }

    public void addPlayer(String email, Integer team) {
        this.teams.put(email, team);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetGameStatisticDtoResponse)) return false;
        GetGameStatisticDtoResponse that = (GetGameStatisticDtoResponse) o;
        return getScoreTeam1() == that.getScoreTeam1() &&
                getScoreTeam2() == that.getScoreTeam2() &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getTeams(), that.getTeams());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScoreTeam1(), getScoreTeam2(), getStatus(), getTeams());
    }

    @Override
    public String toString() {
        return "GetGameStatisticDtoResponse{" +
                "scoreTeam1=" + scoreTeam1 +
                ", scoreTeam2=" + scoreTeam2 +
                ", status='" + status + '\'' +
                ", teams=" + teams +
                '}';
    }
}

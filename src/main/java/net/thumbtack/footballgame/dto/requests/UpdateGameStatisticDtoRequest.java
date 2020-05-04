package net.thumbtack.footballgame.dto.requests;

import java.util.Map;
import java.util.Objects;

public class UpdateGameStatisticDtoRequest {
    private Integer scoreTeam1;
    private Integer scoreTeam2;
    private Map<String, Integer> teams;

    public UpdateGameStatisticDtoRequest() {
    }

    public UpdateGameStatisticDtoRequest (Integer scoreTeam1, Integer scoreTeam2, Map<String, Integer> teams) {
        setScoreTeam1(scoreTeam1);
        setScoreTeam2(scoreTeam2);
        setTeams(teams);
    }

    public Map<String, Integer> getTeams() {
        return teams;
    }

    public void setTeams(Map<String, Integer> teams) {
        this.teams = teams;
    }

    public void addPlayer(String email, Integer team) {
        teams.put(email, team);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateGameStatisticDtoRequest)) return false;
        UpdateGameStatisticDtoRequest that = (UpdateGameStatisticDtoRequest) o;
        return getScoreTeam1() == that.getScoreTeam1() &&
                getScoreTeam2() == that.getScoreTeam2() &&
                Objects.equals(getTeams(), that.getTeams());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScoreTeam1(), getScoreTeam2(), getTeams());
    }

    @Override
    public String toString() {
        return "AssignToTeamsDtoRequest{" +
                ", scoreTeam1=" + scoreTeam1 +
                ", scoreTeam2=" + scoreTeam2 +
                ", teams=" + teams +
                '}';
    }
}

package com.example.Cricket.Game;
import com.example.Team.Data.Team;

public class Match {
    private Team team1;
    private Team team2;
    private Integer numberOfOvers;
    private String verdict;
    private String team1Score;

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public String getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(String team1Score) {
        this.team1Score = team1Score;
    }

    public String getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(String team2Score) {
        this.team2Score = team2Score;
    }

    private String team2Score;

    public void setTeam1(String value)
    {
        team1 = new Team();
        team1.generateTeam(value , 4 , 5 , 2);
    }

    public Match(String team1, String team2, Integer numberOfOvers) {
        this.team1 = new Team();
        this.team1.name = team1;
        this.team2 = new Team();
        this.team2.name = team2;
        this.numberOfOvers = numberOfOvers;
        this.team1.generateTeam(team1 , 4 , 5 , 2);
        this.team2.generateTeam(team2 , 4 , 5 , 2);

    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam2Name(String value)
    {
        team2 = new Team();
        team2.generateTeam(value , 4 , 5 , 2);
    }

    public Team getTeam2()
    {
        return team2;
    }

    public void setNumberOfOvers(Integer value)
    {
        numberOfOvers = value;
    }

    public Integer getNumberOfOvers()
    {
        return numberOfOvers;
    }
}

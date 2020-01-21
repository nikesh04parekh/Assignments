package com.example.Cricket.Game;

public class Match {
    private String team1;
    private String team2;
    private Integer numberOfOvers;

    public void setTeam1(String value)
    {
        team1 = value;
    }

    public String getTeam1()
    {
        return team1;
    }

    public Match(String team1, String team2, Integer numberOfOvers) {
        this.team1 = team1;
        this.team2 = team2;
        this.numberOfOvers = numberOfOvers;
    }

    public void setTeam2(String value)
    {
        team2 = value;
    }

    public String getTeam2()
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

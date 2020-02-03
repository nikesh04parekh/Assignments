package com.example.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {
    public Team [] teamList;
    public String matchResult;
    public String tossResult;
    public int numberOfOvers;

    public Match(String team1, String team2 , int numberOfOvers) {
        this.teamList = new Team[2];
        this.teamList[0] = new Team(team1 , 4 ,4 , 1 ,2);
        this.teamList[1] = new Team(team2 , 4 ,4 , 1 ,2);
        this.numberOfOvers = numberOfOvers;
    }

    public Team getTeam(int flag){

        return this.teamList[flag];
    }
}

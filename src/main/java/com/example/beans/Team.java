package com.example.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {
    String name;
    Integer teamScore;
    Integer wicketsLost;
    String numberOfOversPlayed;
    Player [] playerList;
    int numberOfBatsman;
    int numberOfBowler;
    int numberOfAllRounder;
    int numberOfWicketKeeper;

    public Team(String name, int numberOfBatsman, int numberOfBowler, int numberOfWicketKeeper , int numberOfAllRounder) {
        this.name = name;
        this.numberOfBatsman = numberOfBatsman;
        this.numberOfBowler = numberOfBowler;
        this.numberOfAllRounder = numberOfAllRounder;
        this.numberOfWicketKeeper = numberOfWicketKeeper;
        playerList = new Player[11];
        int i = 0;
        for (; i < numberOfBatsman ; i++){
            playerList[i] = new Player("Player" + i , 0 , 0 , 0 , 0 , 0 , 0 , 0);
            playerList[i].setRole(Player.type.BATSMAN);
            playerList[i].setBattingRating(9.0f);
            playerList[i].setBowlingRating(1.0f);
        }
        for ( ; i < numberOfBatsman + numberOfWicketKeeper ; i++){
            playerList[i] = new Player("Player" + i , 0 , 0 , 0 , 0 , 0 , 0 , 0);
            playerList[i].setRole(Player.type.WICKETKEEPER);
            playerList[i].setBattingRating(6.0f);
            playerList[i].setBowlingRating(0.0f);
        }

        for ( ; i < numberOfBatsman + numberOfWicketKeeper + numberOfAllRounder ; i++){
            playerList[i] = new Player("Player" + i , 0 , 0 , 0 , 0 , 0 , 0 , 0);
            playerList[i].setRole(Player.type.ALLROUNDER);
            playerList[i].setBattingRating(5.0f);
            playerList[i].setBowlingRating(5.0f);
        }

        for ( ; i < 11 ; i++){
            playerList[i] = new Player("Player" + i , 0 , 0 , 0 , 0 , 0 , 0 , 0);
            playerList[i].setRole(Player.type.BOWLER);
            playerList[i].setBattingRating(1.0f);
            playerList[i].setBowlingRating(9.0f);
        }
    }
}

package com.example.beans;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class Player {
    @NotNull
    String name;
    enum type{BOWLER , BATSMAN , ALLROUNDER , WICKETKEEPER};
    type role;
    double strikeRate;
    int numberOfMatchPlayed;
    double economy;
    int numberOfSixes;
    int numberOfFours;
    int numberOfNotOut;
    int numberOfWickets;
    int numberOfCatches;
    int numberOfRuns;
    int numberOfRunsConceded;
    int numberOfBallsBowled;
    int numberOfBallsPlayed;
    int numberOfMaidens;
    float battingRating;
    float bowlingRating;

    public Player(@NotNull String name, int numberOfSixes, int numberOfFours, int numberOfWickets, int numberOfRuns, int numberOfRunsConceded, int numberOfBallsBowled, int numberOfBallsPlayed) {
        this.name = name;
        this.numberOfSixes = numberOfSixes;
        this.numberOfFours = numberOfFours;
        this.numberOfWickets = numberOfWickets;
        this.numberOfRuns = numberOfRuns;
        this.numberOfRunsConceded = numberOfRunsConceded;
        this.numberOfBallsBowled = numberOfBallsBowled;
        this.numberOfBallsPlayed = numberOfBallsPlayed;
    }

}

package com.example.Team.Data;

public class Player {
     String name;
     Double strikeRate;
     Integer numberOfMatchPlayed;
     Double economy;
     Integer numberOfSixes;
     Integer numberOfFours;
     Integer numberOfNotOut;
     Integer numberOfWickets;
     Integer numberOfCatches;
     Integer numberOfRuns;

     Integer getNumberOfRuns() {
        return numberOfRuns;
    }

     void setNumberOfRuns(Integer numberOfRuns) {
        this.numberOfRuns = numberOfRuns;
    }

     String getName() {
        return name;
    }

     Integer getNumberOfWickets() {
        return numberOfWickets;
    }

     void setNumberOfWickets(Integer numberOfWickets) {
        this.numberOfWickets = numberOfWickets;
    }

     void setName(String name) {
        this.name = name;
    }

     Double getStrikeRate() {
        return strikeRate;
    }

     void setStrikeRate(Double strikeRate) {
        this.strikeRate = strikeRate;
    }

     Integer getNumberOfMatchPlayed() {
        return numberOfMatchPlayed;
    }

     void setNumberOfMatchPlayed(Integer numberOfMatchPlayed) {
        this.numberOfMatchPlayed = numberOfMatchPlayed;
    }

     Double getEconomy() {
        return economy;
    }

     void setEconomy(Double economy) {
        this.economy = economy;
    }

     Integer getNumberOfSixes() {
        return numberOfSixes;
    }

     void setNumberOfSixes(Integer numberOfSixes) {
        this.numberOfSixes = numberOfSixes;
    }

     Integer getNumberOfFours() {
        return numberOfFours;
    }

     void setNumberOfFours(Integer numberOfFours) {
        this.numberOfFours = numberOfFours;
    }

     Integer getNumberOfNotOut() {
        return numberOfNotOut;
    }

     void setNumberOfNotOut(Integer numberOfNotOut) {
        this.numberOfNotOut = numberOfNotOut;
    }

     Integer getNumberOfCatches() {
        return numberOfCatches;
    }

     void setNumberOfCatches(Integer numberOfCatches) {
        this.numberOfCatches = numberOfCatches;
    }

}
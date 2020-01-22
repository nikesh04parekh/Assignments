package com.example.Team.Data;

public class Player {
    protected String name;
    protected Double strikeRate;
    protected Integer numberOfMatchPlayed;
    protected Double economy;
    protected Integer numberOfSixes;
    protected Integer numberOfFours;
    protected Integer numberOfNotOut;
    protected Integer numberOfWickets;
    protected Integer numberOfCatches;
    protected Integer numberOfRuns;

    protected Integer getNumberOfRuns() {
        return numberOfRuns;
    }

    protected void setNumberOfRuns(Integer numberOfRuns) {
        this.numberOfRuns = numberOfRuns;
    }

    protected String getName() {
        return name;
    }

    protected Integer getNumberOfWickets() {
        return numberOfWickets;
    }

    protected void setNumberOfWickets(Integer numberOfWickets) {
        this.numberOfWickets = numberOfWickets;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected Double getStrikeRate() {
        return strikeRate;
    }

    protected void setStrikeRate(Double strikeRate) {
        this.strikeRate = strikeRate;
    }

    protected Integer getNumberOfMatchPlayed() {
        return numberOfMatchPlayed;
    }

    protected void setNumberOfMatchPlayed(Integer numberOfMatchPlayed) {
        this.numberOfMatchPlayed = numberOfMatchPlayed;
    }

    protected Double getEconomy() {
        return economy;
    }

    protected void setEconomy(Double economy) {
        this.economy = economy;
    }

    protected Integer getNumberOfSixes() {
        return numberOfSixes;
    }

    protected void setNumberOfSixes(Integer numberOfSixes) {
        this.numberOfSixes = numberOfSixes;
    }

    protected Integer getNumberOfFours() {
        return numberOfFours;
    }

    protected void setNumberOfFours(Integer numberOfFours) {
        this.numberOfFours = numberOfFours;
    }

    protected Integer getNumberOfNotOut() {
        return numberOfNotOut;
    }

    protected void setNumberOfNotOut(Integer numberOfNotOut) {
        this.numberOfNotOut = numberOfNotOut;
    }

    protected Integer getNumberOfCatches() {
        return numberOfCatches;
    }

    protected void setNumberOfCatches(Integer numberOfCatches) {
        this.numberOfCatches = numberOfCatches;
    }

}
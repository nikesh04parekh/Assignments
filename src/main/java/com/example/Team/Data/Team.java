package com.example.Team.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Team {
    public String name;
    public Team.teamPlayer [] playersList;
    private static DecimalFormat df = new DecimalFormat("0.00");
    public class teamPlayer extends com.example.Team.Data.Player {
        public String type;
        public Integer numberOfBallsPlayed;
        public Integer numberOfBallsBowled;
        public Integer numberOfMaidenOvers;
        public Integer numberOfRunsConceded;
        @JsonIgnore
        public Boolean wasLastBatting;
        @JsonIgnore
        public Boolean wasLastBowling;

        public teamPlayer(){

        }
        public teamPlayer(String type, Integer numberOfBallsPlayed, Integer numberOfMaidenOvers , Integer numberOfBallsBowled , Integer numberOfRunsConceded) {
            this.type = type;
            this.numberOfBallsPlayed = numberOfBallsPlayed;
            this.numberOfMaidenOvers = numberOfMaidenOvers;
            this.numberOfBallsBowled = numberOfBallsBowled;
            this.numberOfRunsConceded = numberOfRunsConceded;
            this.wasLastBatting = false;
            this.wasLastBowling = false;
        }

        public Integer getNumberOfFours(){
            return super.getNumberOfFours();
        }

        public Integer getNumberOfSixes(){
            return super.getNumberOfSixes();
        }

        public Integer getNumberOfWickets(){
            return super.getNumberOfWickets();
        }

        public Double getStrikeRate(){
            return super.getStrikeRate();
        }

        public Double getEconomy(){
            return super.getEconomy();
        }

        public Integer getNumberOfRuns(){
            return super.getNumberOfRuns();
        }
    }

    public void generateTeam(String name , Integer batsman , Integer bowler , Integer allRounder){
        this.name = name;
        this.playersList = new Team.teamPlayer[11];
        for (int i = 0 ; i < 11 ; i++) {
            if (i < batsman) {
                this.playersList[i] = new Team().new teamPlayer("batsman", 0, 0 , 0 , 0);
            } else if (i > batsman && i < batsman + allRounder) {
                this.playersList[i] = new Team().new teamPlayer("allRounder", 0, 0 , 0 , 0);
            } else {
                this.playersList[i] = new Team().new teamPlayer("bowler", 0, 0 , 0 , 0);
            }
            playersList[i].setName("Player" + i);
            playersList[i].setNumberOfCatches(0);
            playersList[i].setEconomy(0.0);
            playersList[i].setNumberOfFours(0);
            playersList[i].setNumberOfSixes(0);
            playersList[i].setNumberOfWickets(0);
            playersList[i].setNumberOfRuns(0);
        }
    }

    public void addFour(int index){
        playersList[index].setNumberOfFours(playersList[index].getNumberOfFours() + 1);
    }

    public void addSix(int index){
        playersList[index].setNumberOfSixes(playersList[index].getNumberOfSixes() + 1);
    }

    public void addWicket(int index){
        playersList[index].setNumberOfWickets(playersList[index].getNumberOfWickets() + 1);
    }

    public void addCatch(int index){
        playersList[index].setNumberOfCatches(playersList[index].getNumberOfCatches() + 1);
    }

    public void addRuns(int index , int runs)
    {
        playersList[index].setNumberOfRuns(playersList[index].getNumberOfRuns() + runs);
    }

    public void addRunsConceded(int index , int runs){
        playersList[index].numberOfRunsConceded += runs;
    }

    public String getBattingData(int index){
        StringBuilder result = new StringBuilder();
        if (playersList[index].numberOfBallsPlayed == 0)
            return result.toString();
        result.append(playersList[index].getName());
        if (playersList[index].wasLastBatting)
            result.append("(*)");
        result.append(" ").append(playersList[index].getNumberOfRuns());
        result.append(" ").append(playersList[index].numberOfBallsPlayed);
        result.append(" ").append(playersList[index].getNumberOfFours());
        result.append(" ").append(playersList[index].getNumberOfSixes());
        double strikeRate = (100.0 * playersList[index].getNumberOfRuns()) / playersList[index].numberOfBallsPlayed;
        playersList[index].setStrikeRate(Double.parseDouble(df.format(strikeRate)));
        result.append(" ").append(playersList[index].getStrikeRate());
        return result.toString();
    }

    public String getBowlingData(int index){
        StringBuilder result = new StringBuilder();
        if (playersList[index].numberOfBallsBowled == 0)
            return result.toString();
        result.append(playersList[index].getName());
        result.append(" ").append(playersList[index].numberOfBallsBowled / 6)
                .append(".").append(playersList[index].numberOfBallsBowled % 6);
        result.append(" ").append(playersList[index].numberOfRunsConceded);
        result.append(" ").append(playersList[index].getNumberOfWickets());
        double economy = (6.0 * playersList[index].numberOfRunsConceded) / playersList[index].numberOfBallsBowled;
        playersList[index].setEconomy(Double.parseDouble(df.format(economy)));
        result.append(" ").append(playersList[index].getEconomy());
        return result.toString();
    }
}

package com.example.services;

import com.example.beans.Player;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    public int getNumberOfOversBowled(Player [] playersList , int index){
        return playersList[index].getNumberOfBallsBowled() / 6;
    }

    public void addFour(Player [] playersList , int index){
        playersList[index].setNumberOfFours(playersList[index].getNumberOfFours() + 1);
    }

    public void addSix(Player [] playersList , int index){
        playersList[index].setNumberOfSixes(playersList[index].getNumberOfSixes() + 1);
    }

    public void addWicket(Player [] playersList , int index){
        playersList[index].setNumberOfWickets(playersList[index].getNumberOfWickets() + 1);
    }

    public void addMaiden(Player [] playersList , int index){
        playersList[index].setNumberOfMaidens(playersList[index].getNumberOfMaidens() + 1);
    }

    public void addBallsBowled(Player [] playersList , int index){
        playersList[index].setNumberOfBallsBowled(playersList[index].getNumberOfBallsBowled() + 1);
    }

    public void addBallsPlayed(Player [] playersList , int index){
        playersList[index].setNumberOfBallsPlayed(playersList[index].getNumberOfBallsPlayed() + 1);
    }

    public void addRuns(Player [] playersList , int index , int runs){
        playersList[index].setNumberOfRuns(playersList[index].getNumberOfRuns() + runs);
    }

    public void addRunsConceded(Player [] playersList , int index , int runs) {
        playersList[index].setNumberOfRunsConceded(playersList[index].getNumberOfRunsConceded() + runs);
    }

    public void setStrikeRate(Player [] playersList , int index) {
        double strikeRate = 0.0f;
        if (playersList[index].getNumberOfBallsPlayed() == 0){

        } else {
            strikeRate = (100.0f * playersList[index].getNumberOfRuns()) / playersList[index].getNumberOfBallsPlayed();
        }
        playersList[index].setStrikeRate((float)Math.round(strikeRate * 100.0) / 100.0);
    }

    public void setEconomy(Player [] playersList , int index) {
        double economy = 0.0f;
        if (playersList[index].getNumberOfBallsBowled() == 0){

        } else {
            economy = (6.0f * playersList[index].getNumberOfRunsConceded()) / playersList[index].getNumberOfBallsBowled();
        }
        playersList[index].setEconomy((float)Math.round(economy * 100.0) / 100.0);
    }

    public float getNormalizedBowlingRating( Player [] playerList , int index){
        return playerList[index].getBowlingRating() / getMaxBowlingRating(playerList);
    }

    public float getNormalizedBattingRating( Player [] playerList , int index){
        return playerList[index].getBattingRating() / getMaxBattingRating(playerList);
    }

    public float getMaxBowlingRating(Player [] playerList){
        float ans = 0.0f;
        for (Player player : playerList){
            if (player.getBowlingRating() > ans)
                ans = player.getBowlingRating();
        }
        return ans;
    }

    public float getMaxBattingRating(Player [] playerList){
        float ans = 0.0f;
        for (Player player : playerList){
            if (player.getBattingRating() > ans)
                ans = player.getBattingRating();
        }
        return ans;
    }
}

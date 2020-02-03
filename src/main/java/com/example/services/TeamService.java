package com.example.services;

import com.example.beans.Match;
import com.example.beans.Player;
import com.example.beans.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TeamService {

    @Autowired
    private PlayerService playerService;

    public void setStrikeRateAndEconomy(int flagForInnings , Team [] team){
        for (int i = 0 ; i < 11 ; i++) {
            playerService.setStrikeRate(team[flagForInnings].getPlayerList(), i);
            playerService.setEconomy(team[flagForInnings].getPlayerList(), i);
        }
    }

    private int getNumberOfBallsLeft(String [] str , int numberOfOvers){
        return 6 * numberOfOvers - (Integer.parseInt(str[0]) * 6 + Integer.parseInt(str[1]));
    }

    public void setVerdict(Match match){
        StringBuilder result = new StringBuilder();
        Team [] team = match.getTeamList();
        if (team[0].getTeamScore() > team[1].getTeamScore()) {
            String margin = Integer.toString(team[0].getTeamScore() - team[1].getTeamScore());
            result.append(team[0].getName()).append(" won by a margin of ").append(margin);
            result.append(" runs.");
        } else if ( team[0].getTeamScore() < team[1].getTeamScore()) {
            String wicketsLeft = Integer.toString(10 - team[1].getWicketsLost());
            result.append(team[1].getName()).append(" won by ").append(wicketsLeft).append(" wickets");
            String [] foo = team[1].getNumberOfOversPlayed().split("[.]");
            int ballsLeft = getNumberOfBallsLeft(foo , match.getNumberOfOvers());
            if (ballsLeft == 0);
            else {
                result.append(" and ").append(ballsLeft).append(" balls left.");
            }
        }
        else if (match.getNumberOfOvers() == 0) {
            result.setLength(0);
            result.append("Number of overs cannot be zero");
        }
        else{
            result.append("Match tied");
        }
        match.setMatchResult(result.toString());
    }

    private void simulateBowl(int flagForInnings , Team [] team , int runs , int bowlerIndex , int strikerIndex , int nonStrikerIndex){
        playerService.addBallsPlayed(team[flagForInnings].getPlayerList() , strikerIndex);
        playerService.addBallsBowled(team[1 - flagForInnings].getPlayerList() , bowlerIndex);
        if (runs == 7){
            playerService.addWicket(team[1 - flagForInnings].getPlayerList() , bowlerIndex);
        } else if (runs != 0){
            playerService.addRuns(team[flagForInnings].getPlayerList() , strikerIndex , runs);
            playerService.addRunsConceded(team[1 - flagForInnings].getPlayerList() , bowlerIndex , runs);
            if (runs == 4) {
                playerService.addFour(team[flagForInnings].getPlayerList(), strikerIndex);
            } else if (runs == 6){
                playerService.addSix(team[flagForInnings].getPlayerList(), strikerIndex);
            }
        }
    }

    private void addMaidenOver(int flagForInnings , Team [] team , int bowlerIndex){
        playerService.addMaiden(team[1 - flagForInnings].getPlayerList() , bowlerIndex);
    }

    private int getRandomValue(int flagForInnings , Random randomForRuns , Team[] team , int bowlerIndex , int strikerIndex){
        float normalizedBowlerRating = playerService.getNormalizedBowlingRating(team[1 - flagForInnings].getPlayerList() , bowlerIndex);
        float normalizedBatsmanRating = playerService.getNormalizedBattingRating(team[flagForInnings].getPlayerList() , strikerIndex);
        float normalizedFraction = normalizedBatsmanRating / normalizedBowlerRating;
        float normalizedValue = (float)randomForRuns.nextGaussian() + 21 / 8.0f;
        if (normalizedFraction > 1){
            normalizedValue += normalizedFraction;
        } else
            normalizedValue -= normalizedFraction;

        int value = Math.round(normalizedValue);
        value = Math.max(value , 0);
        value = Math.min(value , 7);
        if (value == 0){
            value = 7;
        } else
            value--;
        return value;
    }

    private int getCurrentBowlerIndex(Random randomForBowlerIndex , Player [] playerList , int bowlerIndex , int numberOfOvers){
        int currentBowlerIndex = 0;
        while (true) {
            currentBowlerIndex = (randomForBowlerIndex.nextInt(6)) + 5;
            if (currentBowlerIndex == bowlerIndex)
                continue;
            if (playerService.getNumberOfOversBowled(playerList , currentBowlerIndex) >= Math.ceil(numberOfOvers / 5.0f)) {
            } else
                break;
        }
        return currentBowlerIndex;
    }

    public void runInnings(int flagForInnings , int target , int numberOfOvers , Team [] team) {
        int wickets = 0;
        int runs = 0;
        int numberOfBalls = 6 * numberOfOvers;
        Random randomForRuns = new Random();
        Random randomForBowlerIndex = new Random();
        int strikerIndex = 0;
        int nonStrikerIndex = 1;
        int bowlerIndex = -1;
        for (int i = 0; i < numberOfOvers; i++) {
            int currentBowlerIndex = getCurrentBowlerIndex(randomForBowlerIndex , team[1 - flagForInnings].getPlayerList() , bowlerIndex , numberOfOvers);
            int value = getRandomValue(flagForInnings , randomForRuns , team , bowlerIndex , strikerIndex);
            bowlerIndex = currentBowlerIndex;
            int runsInThisOver = 0;
            for (int j = 0; j < 6; j++) {
                numberOfBalls--;
                simulateBowl(flagForInnings, team, value, bowlerIndex, strikerIndex, nonStrikerIndex);
                if (value == 7) {
                    wickets++;
                    if (wickets == 10) {
                        break;
                    }
                    strikerIndex = Math.max(strikerIndex, nonStrikerIndex) + 1;
                } else if (value != 0) {
                    runs += value;
                    runsInThisOver += value;
                    if (value % 2 == 1) {
                        int swapHelper = strikerIndex;
                        strikerIndex = nonStrikerIndex;
                        nonStrikerIndex = swapHelper;
                    }
                }
                if (flagForInnings == 1 && runs >= target) {
                    break;
                }
            }
            if (runsInThisOver == 0)
                addMaidenOver(flagForInnings, team, bowlerIndex);
            if (wickets == 10 || (flagForInnings == 1 && runs >= target))
                break;
            int swaphelper = strikerIndex;
            strikerIndex = nonStrikerIndex;
            nonStrikerIndex = swaphelper;
        }
        team[flagForInnings].setTeamScore(runs);
        team[flagForInnings].setWicketsLost(wickets);
        int ballsPlayed = 6 * numberOfOvers - numberOfBalls;
        team[flagForInnings].setNumberOfOversPlayed(ballsPlayed / 6 + "." + ballsPlayed % 6);
    }
}

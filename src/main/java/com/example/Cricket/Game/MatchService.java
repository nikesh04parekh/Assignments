package com.example.Cricket.Game;

import com.example.Team.Data.Team;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class MatchService {

    private Match match;
    protected void setTossData(){
        Random rand = new Random();
        int tossData = rand.nextInt();
        int winningTeamDecision = rand.nextInt();
        StringBuilder result = new StringBuilder();
        if (tossData == 0)
            result.append(match.getTeam1().name);
        else
            result.append(match.getTeam2().name);
        result.append(" won the toss and decided to ");
        if (winningTeamDecision == 0){
            result.append("bat");
        }
        else
            result.append("bowl");
        if (tossData + winningTeamDecision == 1){
        String swapHelper = match.getTeam1().name;
        match.getTeam1().setName(match.getTeam2().getName());
        match.getTeam2().setName(swapHelper);
        }
        match.setTossData(result.toString());
    }
    protected void setMatch(String team1Name , String team2Name , Integer numberOfOvers)
    {
        match = new Match(team1Name , team2Name , numberOfOvers);
    }
    public String generateScore(String team , Pair < Pair<Integer , Integer> , Integer> a)
    {
        int ballsConsumed = (match.getNumberOfOvers() * 6) - a.second;
        StringBuilder result = new StringBuilder();
        int overs = ballsConsumed / 6;
        int balls = ballsConsumed % 6;
        result.append(team).append(": ").append(Integer.toString(a.first.first)).append("/").append(Integer.toString(a.first.second)).append("( ").append(overs);
        if (balls != 0)
            result.append(".").append(Integer.toString(balls));
        result.append(" overs )");
        return result.toString();
    }
    private void setStrikeRateAndEconomy(Team team){
        for (int i = 0 ; i < 11 ; i++)
        {
            team.setStrikeRate(i);
            team.setEconomy(i);
        }
    }
    public Match generateWinner()
    {
        Pair < Pair < Integer , Integer > , Integer > scoreOfTeam1 = runInstance(0 , 0);
        Pair < Pair < Integer , Integer > , Integer > scoreOfTeam2 = runInstance(scoreOfTeam1.first.first + 1 , 1);
        String team1Score = generateScore(match.getTeam1().name , scoreOfTeam1);
        match.setTeam1Score(team1Score);
        String team2Score = generateScore(match.getTeam2().name , scoreOfTeam2);
        match.setTeam2Score(team2Score);
        setStrikeRateAndEconomy(match.getTeam1());
        setStrikeRateAndEconomy(match.getTeam2());
        if (scoreOfTeam1.first.first > scoreOfTeam2.first.first)
        {
            StringBuilder verdict = new StringBuilder();
            String margin = Integer.toString(scoreOfTeam1.first.first - scoreOfTeam2.first.first);
            verdict.append(match.getTeam1().name).append(" won by a margin of ").append(margin);
            if (scoreOfTeam1.first.first - scoreOfTeam2.first.first == 1) {
                verdict.append(" run");
            }
            else {
                verdict.append(" runs");
            }
            match.setVerdict(verdict.toString());
        }
        else if (scoreOfTeam1.first.first < scoreOfTeam2.first.first)
        {
            String wicketsLeft = Integer.toString(10 - scoreOfTeam2.first.second);
            StringBuilder verdict = new StringBuilder();
            verdict.append(match.getTeam2().name).append(" won by ").append(wicketsLeft).append(" wicket");
            if (scoreOfTeam2.first.second < 9) {
                verdict.append("s");
            }
            int oversLeft = scoreOfTeam2.second / 6;
            int ballsLeft = scoreOfTeam2.second % 6;
            if (scoreOfTeam2.second == 0){

            }
            else {
                StringBuilder overs = new StringBuilder();
                overs.append(" and ");
                if (oversLeft == 0){
                    overs.append(Integer.toString(ballsLeft));
                    if (ballsLeft == 1)
                        overs.append(" ball left");
                    else
                        overs.append(" balls left");
                }
                else if (oversLeft != 0 && ballsLeft == 0)
                {
                    overs.append(Integer.toString(oversLeft));
                    if (oversLeft == 1)
                        overs.append(" over left");
                    else
                        overs.append(" overs left");
                }
                else
                {
                    overs.append(Integer.toString(oversLeft)).append(".").append(Integer.toString(ballsLeft)).append(" overs left");
                }
                verdict.append(overs);
                match.setVerdict(verdict.toString());
            }
        }
        else if (match.getNumberOfOvers() == 0) {
            StringBuilder verdict = new StringBuilder();
            verdict.append("Number of overs cannot be zero");
            match.setVerdict(verdict.toString());
        }
        else{
            StringBuilder verdict = new StringBuilder();
            verdict.append("Match tied");
            match.setVerdict(verdict.toString());
        }
        return match;
    }

    private Pair< Pair< Integer , Integer > , Integer > runInstance(Integer target , Integer flag)
    {
        int wickets = 0;
        int runs = 0;
        int numberOfBalls = 6 * match.getNumberOfOvers();
        Random randomForRuns = new Random();
        Random randomForBowlerIndex = new Random();
        int strikerIndex = 0;
        int nonStrikerIndex = 1;
        if (flag == 0) {
            match.getTeam1().playersList[strikerIndex].wasLastBatting = true;
            match.getTeam1().playersList[nonStrikerIndex].wasLastBatting = true;
        }
        else{
            match.getTeam2().playersList[strikerIndex].wasLastBatting = true;
            match.getTeam2().playersList[nonStrikerIndex].wasLastBatting = true;
        }
        int bowlerIndex = -1;
        for (int i = 0 ; i < match.getNumberOfOvers() ; i++)
        {
            Boolean flagForRun = false;
            int currentBowlerIndex = 0;
            while((currentBowlerIndex = (randomForBowlerIndex.nextInt(6)) + 5) == bowlerIndex);
            bowlerIndex = currentBowlerIndex;
            int runsInThisOver = 0;
            for (int j = 0 ; j < 6 ; j++)
            {
                int value = randomForRuns.nextInt(8);
                numberOfBalls--;
                if (flag == 0)
                {
                    match.getTeam2().playersList[bowlerIndex].wasLastBowling = true;
                    match.getTeam1().playersList[strikerIndex].numberOfBallsPlayed++;
                    match.getTeam2().playersList[bowlerIndex].numberOfBallsBowled++;
                    if (value == 7)
                    {
                        match.getTeam2().addWicket(bowlerIndex);
                        match.getTeam1().playersList[strikerIndex].wasLastBatting = false;
                        wickets = wickets + 1;
                        if (wickets == 10){
                            break;
                        }
                        strikerIndex = Math.max(strikerIndex , nonStrikerIndex) + 1;
                        match.getTeam1().playersList[strikerIndex].wasLastBatting = true;
                    }
                    else if (value != 0)
                    {
                        runs += value;
                        runsInThisOver += value;
                        match.getTeam2().addRunsConceded(bowlerIndex , value);
                        match.getTeam1().addRuns(strikerIndex , value);
                        if (value == 4)
                            match.getTeam1().addFour(strikerIndex);
                        else if (value == 6){
                            match.getTeam1().addSix(strikerIndex);
                        }
                        else if (value % 2 == 1){
                            int swapHelper = strikerIndex;
                            strikerIndex = nonStrikerIndex;
                            nonStrikerIndex = swapHelper;
                        }
                    }
                }
                else {
                    match.getTeam1().playersList[bowlerIndex].wasLastBowling = true;
                    match.getTeam1().playersList[bowlerIndex].numberOfBallsBowled++;
                    match.getTeam2().playersList[strikerIndex].numberOfBallsPlayed++;
                    if (value == 7){
                        match.getTeam1().addWicket(bowlerIndex);
                        match.getTeam2().playersList[strikerIndex].wasLastBatting = false;
                        wickets++;
                        if (wickets == 10) {
                            break;
                        }
                        strikerIndex = Math.max(strikerIndex , nonStrikerIndex) + 1;
                        match.getTeam2().playersList[strikerIndex].wasLastBatting = true;
                    }
                    else if (value != 0) {
                        runs += value;
                        runsInThisOver += value;
                        match.getTeam1().addRunsConceded(bowlerIndex, value);
                        match.getTeam2().addRuns(strikerIndex, value);
                        if (value == 4)
                            match.getTeam2().addFour(strikerIndex);
                        else if (value == 6) {
                            match.getTeam2().addSix(strikerIndex);
                        } else if (value % 2 == 1) {
                            int swapHelper = strikerIndex;
                            strikerIndex = nonStrikerIndex;
                            nonStrikerIndex = swapHelper;
                        }
                    }
                    if (runs >= target) {
                        break;
                    }
                }
            }
            if (runsInThisOver == 0) {
                if (flag == 0)
                    match.getTeam2().playersList[bowlerIndex].numberOfMaidenOvers++;
                else
                    match.getTeam1().playersList[bowlerIndex].numberOfMaidenOvers++;
            }
            if (wickets == 10)
                break;
            if (flag == 1 && runs >= target)
                break;
        }
        return new Pair< Pair < Integer, Integer > , Integer > (new Pair(runs , wickets) , numberOfBalls);
    }
}

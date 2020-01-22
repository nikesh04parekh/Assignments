package com.example.Cricket.Game;

import com.example.Team.Data.Team;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class MatchService {

    private Match match;
    protected Integer getTossResult()
    {
        Random rand = new Random();
        return rand.nextInt(2);
    }
    protected void setMatch(String team1Name , String team2Name , Integer numberOfOvers)
    {
        match = new Match(team1Name , team2Name , numberOfOvers);
    }
    public String generateScore(String team , Pair < Pair<Integer , Integer> , Integer> a)
    {
        int ballsConsumed = (match.getNumberOfOvers() * 6) - a.second;
        StringBuilder result = new StringBuilder();
        //result.append(a.toString());
        int overs = ballsConsumed / 6;
        int balls = ballsConsumed % 6;
        //result.append(Integer.toString(a.second) + " " + Integer.toString((numberOfOvers * 6) - a.second) + "<br>");
        result.append(team).append(": ").append(Integer.toString(a.first.first)).append("/").append(Integer.toString(a.first.second)).append("( ").append(overs);
        if (balls != 0)
            result.append(".").append(Integer.toString(balls));
        result.append(" overs )" + "<br>");
        return result.toString();
    }
    public Match generateWinner()
    {
        Pair < Pair < Integer , Integer > , Integer > scoreOfTeam1 = runInstance(0 , 0);
        Pair < Pair < Integer , Integer > , Integer > scoreOfTeam2 = runInstance(scoreOfTeam1.first.first + 1 , 1);
        StringBuilder result = new StringBuilder();
        result.append("<div style=\"text-align:center;margin-top:50px\">");
        //result.append(scoreOfTeam1.toString() + "<br>");
        String team1Score = generateScore(match.getTeam1().name , scoreOfTeam1);
        match.setTeam1Score(team1Score);
        result.append(team1Score);
        //result.append(scoreOfTeam1.toString() + "<br>");
        String team2Score = generateScore(match.getTeam2().name , scoreOfTeam2);
        match.setTeam2Score(team2Score);
        result.append(team2Score);
        result.append("<b>");
        if (scoreOfTeam1.first.first > scoreOfTeam2.first.first)
        {
            StringBuilder verdict = new StringBuilder();
            String margin = Integer.toString(scoreOfTeam1.first.first - scoreOfTeam2.first.first);
            result.append(match.getTeam1().name).append(" won by a margin of ").append(margin);
            verdict.append(match.getTeam1().name).append(" won by a margin of ").append(margin);
            if (scoreOfTeam1.first.first - scoreOfTeam2.first.first == 1) {
                result.append(" run");
                verdict.append(" run");
            }
            else {
                result.append(" runs");
                verdict.append(" runs");
            }
            match.setVerdict(verdict.toString());
        }
        else if (scoreOfTeam1.first.first < scoreOfTeam2.first.first)
        {
            String wicketsLeft = Integer.toString(10 - scoreOfTeam2.first.second);
            StringBuilder verdict = new StringBuilder();
            result.append(match.getTeam2().name).append(" won by ").append(wicketsLeft).append(" wickets");
            verdict.append(match.getTeam2().name).append(" won by ").append(wicketsLeft).append(" wickets");
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
                result.append(overs);
                verdict.append(overs);
                match.setVerdict(verdict.toString());
            }
        }
        else if (match.getNumberOfOvers() == 0) {
            result.setLength(0);
            StringBuilder verdict = new StringBuilder();
            result.append("Number of overs cannot be zero");
            verdict.append("Number of overs cannot be zero");
            match.setVerdict(verdict.toString());
        }
        else{
            StringBuilder verdict = new StringBuilder();
            result.append("Match tied");
            verdict.append("Match tied");
            match.setVerdict(verdict.toString());
        }
        result.append("<b>");
        result.append("</div>");
        result.append("<br><br><br>");
        appendData(result , 0);
        result.append("<br><br><br>");
        appendData(result , 1);
        return match;
    }
    private void appendData(StringBuilder result , int flag){
        result.append("<p style=\"text-align:center\">");
        if (flag == 0){
            result.append(match.getTeam1().name).append(" ").append("Batting").append("<br>");
        }
        else
            result.append(match.getTeam2().name).append(" ").append("Batting").append("<br>");
        result.append("</p>");
        result.append("\n" +
                "<table style=\"width:100%;text-align:center;padding:0px\" border = \"1\">\n" +
                "  <tr>\n" +
                "    <th style=\"padding:5px\">playerName</th>\n" +
                "    <th>runsScored</th> \n" +
                "    <th>ballsPlayed</th>\n" +
                "    <th>numberOfFours</th>\n" +
                "    <th>numberOfSixes</th>\n" +
                "    <th>strikeRate</th>\n" +
                "  </tr>\n");
        if (flag == 0){
            for (int i = 0 ; i < 11 ; i++){
                String data = match.getTeam1().getBattingData(i);
                if (data.length() == 0)
                    continue;
                result.append("  <tr>\n");
                String [] temp = data.split(" ");
                for (String bar : temp)
                    result.append("<td>").append(bar).append("</td>\n");
                result.append("  </tr>\n");
            }
            result.append("</table>");
        }
        else{
            for (int i = 0 ; i < 11 ; i++){
                String data = match.getTeam2().getBattingData(i);
                if (data.length() == 0)
                    continue;
                result.append("<tr>\n");
                String [] temp = data.split(" ");
                for (String bar : temp)
                    result.append("<td>").append(bar).append("</td>\n");
                result.append("</tr>\n");
            }
            result.append("</table>");
        }
        result.append("<br><br><br>");
        result.append("<p style=\"text-align:center\">");
        if (flag == 0){
            result.append(match.getTeam2().name).append(" ").append("Bowling").append("<br>");
        }
        else
            result.append(match.getTeam1().name).append(" ").append("Bowling").append("<br>");
        result.append("</p>");
        result.append("\n" +
                "<table style=\"width:100%;text-align:center;padding:0px\" border = \"1\">\n" +
                "  <tr>\n" +
                "    <th style=\"padding:5px\">playerName</th>\n" +
                "    <th>numberOfOvers</th> \n" +
                "    <th>runsConceded</th>\n" +
                "    <th>wickets</th>\n" +
                "    <th>economy</th>\n" +
                "  </tr>\n");
        if (flag == 1){
            for (int i = 0 ; i < 11 ; i++){
                String data = match.getTeam1().getBowlingData(i);
                if (data.length() == 0)
                    continue;
                result.append("<tr>\n");
                String [] temp = data.split(" ");
                for (String bar : temp)
                    result.append("<td>").append(bar).append("</td>\n");
                result.append("</tr>\n");
            }
            result.append("</table>");
        }
        else{
            for (int i = 0 ; i < 11 ; i++){
                String data = match.getTeam2().getBowlingData(i);
                if (data.length() == 0)
                    continue;
                result.append("<tr>\n");
                String [] temp = data.split(" ");
                for (String bar : temp)
                    result.append("<td>").append(bar).append("</td>\n");
                result.append("</tr>\n");
            }
            result.append("</table>");
        }
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
            while((currentBowlerIndex = randomForBowlerIndex.nextInt(11)) == bowlerIndex);
            bowlerIndex = currentBowlerIndex;
            for (int j = 0 ; j < 6 ; j++)
            {
                int value = randomForRuns.nextInt(8);
                numberOfBalls--;
                Boolean overLeft = false;
                Boolean runScored = false;
                if (flag == 0) {
                    match.getTeam2().playersList[bowlerIndex].wasLastBowling = true;
                    match.getTeam1().playersList[strikerIndex].numberOfBallsPlayed++;
                    match.getTeam2().playersList[bowlerIndex].numberOfBallsBowled++;
                    if (value == 7){
                        match.getTeam2().addWicket(bowlerIndex);
                        match.getTeam1().playersList[strikerIndex].wasLastBatting = false;
                        wickets = wickets + 1;
                        if (wickets == 10) {
                            if (j != 5)
                                overLeft = true;
                            break;
                        }
                        strikerIndex = Math.max(strikerIndex , nonStrikerIndex) + 1;
                        match.getTeam1().playersList[strikerIndex].wasLastBatting = true;
                    }
                    else if (value != 0)
                    {
                        runScored = true;
                        runs += value;
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
                            if (j != 5)
                                overLeft = true;
                            break;
                        }
                        strikerIndex = Math.max(strikerIndex , nonStrikerIndex) + 1;
                        match.getTeam2().playersList[strikerIndex].wasLastBatting = true;
                    }
                    else if (value != 0){
                        runScored = true;
                        runs += value;
                        match.getTeam1().addRunsConceded(bowlerIndex , value);
                        match.getTeam2().addRuns(strikerIndex , value);
                        if (value == 4)
                            match.getTeam2().addFour(strikerIndex);
                        else if (value == 6){
                            match.getTeam2().addSix(strikerIndex);
                        }
                        else if (value % 2 == 1){
                            int swapHelper = strikerIndex;
                            strikerIndex = nonStrikerIndex;
                            nonStrikerIndex = swapHelper;
                        }
                    }
                    if (runs >= target) {
                        if (j != 5) {
                            overLeft = true;
                            break;
                        }
                    }
                }
                if (!overLeft){
                    if (flag == 0)
                        match.getTeam2().playersList[bowlerIndex].wasLastBowling = false;
                    else
                        match.getTeam1().playersList[bowlerIndex].wasLastBowling = false;
                }
                if (!runScored){
                    if (flag == 0)
                        match.getTeam2().playersList[bowlerIndex].numberOfMaidenOvers++;
                    else
                        match.getTeam1().playersList[bowlerIndex].numberOfMaidenOvers++;
                }
            }
            if (wickets == 10)
                break;
            if (flag == 1 && runs >= target)
                break;
        }
        return new Pair< Pair < Integer, Integer > , Integer > (new Pair(runs , wickets) , numberOfBalls);
    }
}

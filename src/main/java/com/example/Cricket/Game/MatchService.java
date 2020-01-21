package com.example.Cricket.Game;

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
        result.append(team + ": "
                + Integer.toString(a.first.first) + "/" + Integer.toString(a.first.second)
                + "( " + overs);
        if (balls != 0)
            result.append("." + Integer.toString(balls));
        result.append(" overs )" + "<br>");
        return result.toString();
    }
    public String generateWinner()
    {
        Pair < Pair < Integer , Integer > , Integer > scoreOfTeam1 = runInstance(0 , 0);
        Pair < Pair < Integer , Integer > , Integer > scoreOfTeam2 = runInstance(scoreOfTeam1.first.first + 1 , 1);
        StringBuilder result = new StringBuilder();
        //result.append(scoreOfTeam1.toString() + "<br>");
        String team1Score = generateScore(match.getTeam1() , scoreOfTeam1);
        result.append(team1Score);
        //result.append(scoreOfTeam1.toString() + "<br>");
        String team2Score = generateScore(match.getTeam2() , scoreOfTeam2);
        result.append(team2Score);
        if (scoreOfTeam1.first.first > scoreOfTeam2.first.first)
        {
            String margin = Integer.toString(scoreOfTeam1.first.first - scoreOfTeam2.first.first);
            result.append(match.getTeam1() + " won by a margin of " + margin);
            if (scoreOfTeam1.first.first - scoreOfTeam2.first.first == 1)
                result.append(" run");
            else
                result.append(" runs");
        }
        else if (scoreOfTeam1.first.first < scoreOfTeam2.first.first)
        {
            String wicketsLeft = Integer.toString(10 - scoreOfTeam2.first.second);
            result.append(match.getTeam2() + " won by " + wicketsLeft + " wickets");
            Integer oversLeft = scoreOfTeam2.second / 6;
            Integer ballsLeft = scoreOfTeam2.second % 6;
            if (scoreOfTeam2.second == 0);
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
                    overs.append(Integer.toString(oversLeft) + "." + Integer.toString(ballsLeft) + " overs left");
                }
                result.append(overs);
            }
        }
        else if (match.getNumberOfOvers() == 0) {
            result.setLength(0);
            result.append("Number of overs cannot be zero");
        }
        else{
            result.append("Match tied");
        }
        return result.toString();
    }

    private Pair< Pair< Integer , Integer > , Integer > runInstance(Integer target , Integer flag)
    {
        int wickets = 0;
        int runs = 0;
        int numberOfBalls = 6 * match.getNumberOfOvers();
        Random random = new Random();
        while(numberOfBalls > 0)
        {
            numberOfBalls--;
            Integer value = random.nextInt(8);
            if (value == 7)
                wickets++;
            else
                runs = runs + value;
            if (wickets == 10)
                break;
            if (flag == 1 && runs >= target)
                break;
        }
        return new Pair< Pair < Integer, Integer > , Integer > (new Pair(runs , wickets) , numberOfBalls);
    }
}

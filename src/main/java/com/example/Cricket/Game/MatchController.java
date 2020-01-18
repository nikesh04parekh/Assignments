package com.example.Cricket.Game;

import java.util.*;

public class MatchController {
    private String team1;
    private String team2;
    private Integer numberOfOvers;

    public void setTeam1(String value)
    {
        team1 = value;
    }

    public String getTeam1()
    {
        return team1;
    }

    public void setTeam2(String value)
    {
        team2 = value;
    }

    public String getTeam2()
    {
        return team2;
    }

    public void setNumberOfOvers(Integer value)
    {
        numberOfOvers = value;
    }

    public Integer getNumberOfOvers()
    {
        return numberOfOvers;
    }

    public String generateWinner()
    {
        Pair < Pair < Integer , Integer > , Integer > scoreOfTeam1 = runInstance(0 , 0);
        Pair< Pair< Integer , Integer > , Integer > scoreOfTeam2 = runInstance(scoreOfTeam1.first.first + 1 , 1);
        StringBuilder result = new StringBuilder();
        if (scoreOfTeam1.first.first > scoreOfTeam2.first.first)
        {
            String margin = Integer.toString(scoreOfTeam1.first.first - scoreOfTeam2.first.first);
            result.append(team1 + " won by a margin of " + margin);
            if (scoreOfTeam1.first.first - scoreOfTeam2.first.first == 1)
                result.append(" run");
            else
                result.append(" runs");
        }
        else if (scoreOfTeam1.first.first < scoreOfTeam2.first.first)
        {
            String wicketsLeft = Integer.toString(10 - scoreOfTeam2.first.second);
            result.append(team2 + " won by " + wicketsLeft + " wickets");
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
        else if (numberOfOvers == 0)
            result.append("Number of overs cannot be zero");
        else{
            result.append("Match tied");
        }
        return result.toString();
    }

    private Pair< Pair< Integer , Integer > , Integer > runInstance(Integer target , Integer flag)
    {
        int wickets = 0;
        int runs = 0;
        int numberOfBalls = 6 * numberOfOvers;
        while(numberOfBalls > 0)
        {
            numberOfBalls--;
            Random random = new Random();
            Integer value = random.nextInt(7);
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

package com.example.services;

import com.example.beans.Match;
import com.example.beans.Team;
import com.example.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MatchService {

    private Match match;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchRepository matchRepository;

    public void setTossData(String team1 , String team2 , int numberOfOvers){
        setMatch(team1 , team2 , numberOfOvers);
        Random rand = new Random();
        int tossData = rand.nextInt(2);
        int winningTeamDecision = rand.nextInt(2);
        StringBuilder result = new StringBuilder();
        result.append(match.getTeam(tossData).getName());
        result.append(" won the toss and decided to ");
        if (winningTeamDecision == 0){
            result.append("bat");
        }
        else
            result.append("bowl");
        match.setTossResult(result.toString());
        if (tossData + winningTeamDecision == 1){
            swapTeamName(match.getTeam(0) , match.getTeam(1));
        }
    }

    private void swapTeamName(Team team1 , Team team2){
        String swapHelper = team1.getName();
        team1.setName(team2.getName());
        team2.setName(swapHelper);
    }
    public void setMatch(String team1Name , String team2Name , Integer numberOfOvers) {
        match = new Match(team1Name , team2Name , numberOfOvers);
    }
    public Match startMatch(String team1 , String team2 , int numberOfOvers){
        setTossData(team1 , team2 , numberOfOvers);
        teamService.runInnings(0 , 0 , match.getNumberOfOvers() , match.getTeamList());
        teamService.runInnings(1 , match.getTeam(0).getTeamScore() + 1 , match.getNumberOfOvers() , match.getTeamList());
        teamService.setStrikeRateAndEconomy(0 , match.getTeamList());
        teamService.setStrikeRateAndEconomy(1 , match.getTeamList());
        teamService.setVerdict(match);
        //matchRepository.addMatch(match);
        return match;
    }
}

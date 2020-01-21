package com.example.Cricket.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping("/{team1}&{team2}&{numberOfOvers}")
    public String executeMatch(@PathVariable String team1 , @PathVariable String team2 , @PathVariable Integer numberOfOvers){
        if (matchService.getTossResult() == 1)
        {
            String swapHelper = team1;
            team1 = team2;
            team2 = swapHelper;
        }
        matchService.setMatch(team1 , team2 , numberOfOvers);

        return matchService.generateWinner();
    }
}

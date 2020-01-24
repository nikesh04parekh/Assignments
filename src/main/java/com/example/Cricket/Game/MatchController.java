package com.example.Cricket.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping("/{team1}&{team2}&{numberOfOvers}")
    public Match executeMatch(@PathVariable String team1 , @PathVariable String team2 , @PathVariable Integer numberOfOvers){

        matchService.setMatch(team1 , team2 , numberOfOvers);
        matchService.setTossData();
        return matchService.generateWinner();
    }
}

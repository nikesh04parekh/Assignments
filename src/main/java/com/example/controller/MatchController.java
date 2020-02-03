package com.example.controller;
import com.example.beans.Match;
import com.example.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping(value = "/match/team1/{team1}/team2/{team2}/overs/{numberOfOvers}")
    public Match executeMatch(@PathVariable String team1, @PathVariable String team2, @PathVariable Integer numberOfOvers){

        return matchService.startMatch(team1 , team2 , numberOfOvers);
    }

}

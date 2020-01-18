package com.example.Cricket.Game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIcontroller {

    @GetMapping("/")
    @ResponseBody
    public String index1(@RequestParam(name = "team1") String team1 , @RequestParam(name = "team2") String team2 , @RequestParam(name = "numberOfOvers") Integer numberOfOvers){
        MatchController matchController = new MatchController();
        matchController.setNumberOfOvers(numberOfOvers);
        matchController.setTeam1(team1);
        matchController.setTeam2(team2);
        return matchController.generateWinner();
    }

}

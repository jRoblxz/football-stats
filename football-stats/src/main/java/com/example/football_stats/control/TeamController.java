package com.example.football_stats.control;

import com.example.football_stats.sevice.FootballService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//aqui são os Endpoints 
@RestController
@RequestMapping("/api/teams")
public class TeamController {
    
    private final FootballService footballService;

    @GetMapping("")
    public String mensagem(){
        return "Hello World!";
    }

    public TeamController(FootballService footballService) {
        this.footballService = footballService;
    }

    @GetMapping("/{teamName}")
    public String getTeamDetails(@PathVariable String teamName) {
        return footballService.getTeamDetails(teamName);
    }

    @GetMapping("/{teamId}/statistics")
    public String getTeamStatistics(@PathVariable String teamId) {
        return footballService.getTeamStatistics(teamId);
    }
}

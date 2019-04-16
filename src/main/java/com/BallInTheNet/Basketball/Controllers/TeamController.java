package com.BallInTheNet.Basketball.Controllers;

import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import com.BallInTheNet.Basketball.Models.Game;
import com.BallInTheNet.Basketball.Models.Team;
import com.BallInTheNet.Basketball.Service.GameService;
import com.BallInTheNet.Basketball.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teamList")
    public List<Team> teamList(){
        return teamService.getTeams();
    }

    @GetMapping("/teamByName/{teamName}")
    public List<Team> teamListByTeamName(@PathVariable String teamName){
        return teamService.findByName(teamName);
    }

    @GetMapping("/teamByTotalScore/{totalScore}")
    public List<Team> teamListByTotalScore(@PathVariable Long totalScore){
        return teamService.findByTotalScore(totalScore);
    }

    @GetMapping("/teamByNameEquals/{teamName}")
    public Team findByNameEquals(@PathVariable String teamName){
        return teamService.findByNameEquals(teamName);
    }

    @PostMapping("/addNewTeam")
    public Long addNewTeam(@RequestBody Team team){
        return teamService.addTeam(team);
    }

    @DeleteMapping("/removeTeam/{id}")
    public boolean remove(@PathVariable Long id) {
        return teamService.removeTeam(id);
    }

    @PutMapping("/editTeam/{id}")
    public ResponseEntity editTeam(@RequestBody Team team, @PathVariable Long id) {
        team = teamService.editTeam(id, team);
        if (team == null) {
            return ResponseEntity.badRequest().body("Zły request");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(team);
        }
    }

   /* @GetMapping("/{name}/game") //nie dziala
    public ResponseEntity<Collection<Game>> getGameTeam(@PathVariable String name) { //accesing game from a given team
        Team team = teamService.findByNameEquals(name);

        if (team != null) {
            return new ResponseEntity<>(team.get, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }*/

}

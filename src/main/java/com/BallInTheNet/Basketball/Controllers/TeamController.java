package com.BallInTheNet.Basketball.Controllers;

import com.BallInTheNet.Basketball.Models.Team;
import com.BallInTheNet.Basketball.Service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    private Logger logger = LoggerFactory.getLogger(TeamController.class);
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teamList")
    public ResponseEntity teamList() {
        List<Team> listOfTeams = teamService.getTeams();
        if (listOfTeams.isEmpty()) {
            logger.info("There is not any teams in DataBase");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of all teams");
        return new ResponseEntity<> (listOfTeams, HttpStatus.OK);
    }

    @GetMapping("/teamByName/{teamName}")
    public ResponseEntity teamListByTeamName(@PathVariable String teamName) {
        List<Team> teamList = teamService.findByName(teamName);
        if (teamList.isEmpty()) {
            logger.info("There is not any team name {}", teamName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teamList, HttpStatus.OK);
    }

    @GetMapping("/teamByTotalScoreBiggerThen/{totalScore}")
    public ResponseEntity<List<Team>> teamListByTotalScore(@PathVariable Long totalScore) {
        List<Team> listTeam = teamService.findByTotalScore(totalScore);
        if (listTeam.isEmpty()) {
            logger.info("there isn't any team with totalScore {}", totalScore);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listTeam, HttpStatus.OK);
    }

    @GetMapping("/teamByNameEquals/{teamName}")
    public ResponseEntity<Team> findByNameEquals(@PathVariable String teamName) {
        Team team = teamService.findByNameEquals(teamName);
        if (team == null) {
            logger.info("There isn't any team name {}", teamName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Finding team with name {}", teamName);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PostMapping("/addNewTeam")
    public ResponseEntity<?> addNewTeam(@RequestBody Team team) {
        logger.info("Added team : {}", team);
        if (teamService.isTeamExist(team)) {
            logger.info("There is this team in database, team : {} ", team);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Long id = teamService.addTeam(team);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/removeTeam/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable Long id) {
        logger.info("Removing team with id : {}", id);
        Boolean isDeleted = teamService.removeTeam(id);
        if (!isDeleted) {
            logger.info("Team with id {} wasn't removed", id);
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
        logger.info("Team with id {} is removed", id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

    @PutMapping("/editTeam/{id}")
    public ResponseEntity editTeam(@RequestBody Team team, @PathVariable Long id) {
        team = teamService.editTeam(id, team);
        logger.info("Editing team with id : {}", id);
        if (team == null) {
            logger.info("Given team = null");
            return ResponseEntity.badRequest().body("Bad request");
        }
        logger.info("Team with id : {}, was edited.");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(team);
    }

}

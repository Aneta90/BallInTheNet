package com.BallInTheNet.Basketball.Controllers;

import com.BallInTheNet.Basketball.Models.Player;
import com.BallInTheNet.Basketball.Service.PlayerService;
import com.BallInTheNet.Basketball.Util.CustomError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    private final
    PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/playersList")
    public ResponseEntity playersList() {
        List<Player> playersList = playerService.getListOfPlayer();
        if (playersList.isEmpty()) {
            logger.warn("Base is empty. Firstly add some players.");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.info("List of all players");
        return new ResponseEntity<>(playersList, HttpStatus.OK);
    }

    @GetMapping("/playerBySurName/{name}")
    public ResponseEntity playersWithGivenName(@PathVariable String name) {
        List<Player> playersList = playerService.playersWithGivenName(name);
        if (playersList.isEmpty()) {
            logger.info("There is no player name {}", name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.warn("List of players with name {}", name);
        return new ResponseEntity<>(playersList, HttpStatus.OK);
    }


    @GetMapping("/findPlayersInTeam/{teamName}")
    public ResponseEntity findPlayersInTeam(@PathVariable String teamName) {
        List<Player> playersListInTeam = playerService.findAllPlayersInTeam(teamName);
        if (playersListInTeam.isEmpty()) {
            logger.warn("In team {}, there are not any players!", teamName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("List of players in team {}", teamName);
        return new ResponseEntity<>(playersListInTeam, HttpStatus.OK);
    }

    @GetMapping("/findOlderThen/{age}")
    public ResponseEntity playersOlderThen(@PathVariable int age) {
        List<Player> playersListOlderThen = playerService.findOlderThen(age);
        if (playersListOlderThen.isEmpty()) {
            logger.warn("There is not any player older then {}", age);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("List of players older then {}", age);
        return new ResponseEntity<>(playersListOlderThen, HttpStatus.OK);
    }

    @GetMapping("findYoungerThen/{age}")
    public ResponseEntity playersYoungerThen(@PathVariable int age) {
        List<Player> playersListYoungerThen = playerService.findYoungerThen(age);
        if (playersListYoungerThen.isEmpty()) {
            logger.warn("There is not any player younger then {}", age);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("List of players younger then {}", age);
        return new ResponseEntity<>(playersListYoungerThen, HttpStatus.OK);
    }

    @PostMapping("/addPlayer")
    public ResponseEntity<?> addPlayer(@RequestBody Player player) {
        logger.info("Adding player : {}", player);
        if (playerService.doesPlayerExist(player)) {
            logger.warn("There is this player in database. Player : {}", player);
            return new ResponseEntity<>(String.valueOf(new CustomError("Unable to create Player. Player " +
                    player + " already exists.")), HttpStatus.CONFLICT);
        }
        Long createdPlayerId = playerService.savePlayer(player);
        return new ResponseEntity<>(createdPlayerId, HttpStatus.CREATED);
    }

    @PutMapping("/editPlayer/{id")
    public ResponseEntity editPlayer(@RequestBody Player player, @PathVariable Long id) {
        logger.info("Edit player with id {}", id);
        Player player1 = playerService.editPlayer(player, id);
        if (player1 == null) {
            logger.error("Something went wrong...");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(player1, HttpStatus.UPGRADE_REQUIRED);
    }

    @DeleteMapping("/removePlayer/{id}")
    public ResponseEntity<Boolean> removePlayer(@PathVariable Long id) {
        boolean isRemoved = playerService.removePlayer(id);
        if (!isRemoved) {
            logger.error("Player with Id {}, wasn't removed", id);
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
        logger.info("Player with id {}, is deleted", id);
        return new ResponseEntity<>(isRemoved, HttpStatus.OK);

    }

    @GetMapping("/injuredPlayers")
    public ResponseEntity<List<Player>> injuredPlayersList() {
        List<Player> injuredPlayersList = playerService.listOfInjuredPlayers();
        if (injuredPlayersList.isEmpty()) {
            logger.info("There isn't any injured player now.");
            return new ResponseEntity<>(injuredPlayersList, HttpStatus.OK);
        }
        logger.info("List of injured players");
        return new ResponseEntity<>(injuredPlayersList, HttpStatus.OK);
    }
}


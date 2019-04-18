package com.BallInTheNet.Basketball.Controllers;

import com.BallInTheNet.Basketball.Models.Player;
import com.BallInTheNet.Basketball.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PlayerController {

    private final
    PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/playerList")
    public List<Player> playerList() {
        return playerService.getListOfPlayer();
    }

    @GetMapping("/playerByName/{name}")
    public List<Player> playerWithGivenName(@PathVariable String name) {
        return playerService.playersWithGivenName(name);
    }

    @GetMapping("/findPlayersInTeam/{teamName")
    public List<Player> findPlayersInTeam(@PathVariable String teamName) {
        return playerService.findAllPlayersInTeam(teamName);
    }

    @GetMapping("/findOlderThen/{age}")
    public List<Player> playersOlderThen(@PathVariable int age) {
        return playerService.findOlderThen(age);
    }

    @GetMapping("findYoungerThen/{age}")
    public List<Player> playersYoungerThen(@PathVariable int age) {
        return playerService.findYoungerThen(age);
    }

    @PostMapping("/addPlayer")
    public Long addPlayer(@RequestBody Player player) {
        return playerService.savePlayer(player);
    }

//    @PutMapping("/updatePlayer")
//    public Player updatePlayer(@RequestBody Player player) {
//        return playerService.updatePlayer(player);
//    }

    @PutMapping("/editPlayer/{id")
    public Player editPlayer(@RequestBody Player player, @PathVariable Long id) {
        return playerService.editPlayer(player, id);
    }

    @DeleteMapping("/removePlayer/{id}")
    public boolean removePlayer(@PathVariable Long id) {
        return playerService.removePlayer(id);
    }

    @GetMapping
    public List<Player> injuredPlayersList() {
        return playerService.listOfInjuredPlayers();
    }
}


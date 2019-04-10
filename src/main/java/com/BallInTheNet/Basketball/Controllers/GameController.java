package com.BallInTheNet.Basketball.Controllers;

import com.BallInTheNet.Basketball.Models.Game;
import com.BallInTheNet.Basketball.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/gameList")
    public List<Game> gameList(){
        return gameService.getGames();
    }

    @GetMapping("/gameByTeamHome/{teamHome}")
    public List<Game> gameListByTeamHome(@PathVariable String teamHome){
       return gameService.findByHomeTeam(teamHome);
    }

    @GetMapping("/gameByTeamAway/{teamAway}")
    public List<Game> gameListByTeamAway(@PathVariable String teamAway){
         return gameService.findByHomeAway(teamAway);
    }

    @GetMapping("/gameByDate")
    public List<Game> findFutureGames(){
    //    return gameService.findFutureGames;
        return null;
    }

    @PostMapping("/addNewGame")
    public Long addNewPlayer(@RequestBody Game game){
        return null; //to do
    }

    @DeleteMapping("/removeGame/{id}")
    public boolean remove(@PathVariable Long id) {
        return false; // to do
    }

    @PutMapping("/editGame/{id}")
    public boolean edit(@RequestBody Game game,@PathVariable Long id) {

        try {
       //   return gameService.(course); to do
        } catch (Exception ex) {
            return false;
        }
        return false;
    }





}

package com.BallInTheNet.Basketball.Controllers;

import com.BallInTheNet.Basketball.Models.Game;
import com.BallInTheNet.Basketball.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return gameService.findByFutureGames();
    }

    @PostMapping("/addNewGame")
    public Long addNewGame(@RequestBody Game game){
        return gameService.addGame(game);
    }

    @DeleteMapping("/removeGame/{id}")
    public boolean remove(@PathVariable Long id) {
        return gameService.removeGame(id);
    }

    @PutMapping("/editGame/{id}")
    public ResponseEntity editGame(@RequestBody Game game, @PathVariable Long id) {
        game = gameService.editGame(id, game);

        if (game == null) {
            return ResponseEntity.badRequest().body("Zły request");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(game);
        }
    }
    }


package com.BallInTheNet.Basketball.Controllers;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryGame;
import com.BallInTheNet.Basketball.Models.Game;
import com.BallInTheNet.Basketball.Models.Team;
import com.BallInTheNet.Basketball.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    //private final RepositoryGame repositoryGame;

    @Autowired
    public GameController(GameService gameService /*,RepositoryGame repositoryGame*/) {
        this.gameService = gameService;
      //  this.repositoryGame=repositoryGame;
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

    @GetMapping("/gameById/{game_id}")
    public Game findGameByGameId(@PathVariable Long game_id){
      return gameService.findGameById(game_id);
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

  /*  @RequestMapping(value="/{game_id}/team",method = RequestMethod.GET) //próba nie działa
    public ResponseEntity<Collection<Team>> getTeamGame(@PathVariable long game_id) { //accesing team from a given game
        Game game = gameService.findGameById(game_id);
        //GameEntity game = repositoryGame.findOne(game_id);

        if (game != null) {
            return new ResponseEntity<>(game.getTeam(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }*/

    }



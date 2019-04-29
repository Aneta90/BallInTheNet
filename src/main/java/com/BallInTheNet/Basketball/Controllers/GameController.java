package com.BallInTheNet.Basketball.Controllers;

import com.BallInTheNet.Basketball.Models.Game;
import com.BallInTheNet.Basketball.Service.GameService;
import com.BallInTheNet.Basketball.Util.CustomError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    public GameController(GameService gameService /*,RepositoryGame repositoryGame*/) {
        this.gameService = gameService;
    }

    @GetMapping("/gameList")
    public ResponseEntity<List<Game>> gameList(){
        List<Game> gameList = gameService.getGames();
        if (gameList.isEmpty()) {
            logger.info("Base is empty. Firstly add some games.");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.info("List of all games");
        return (ResponseEntity<List<Game>>) gameList;
    }

    @GetMapping("/gameByTeamHome/{teamHomeName}")
    public ResponseEntity<List<Game>> gameListByTeamHome(@PathVariable String teamHomeName){
        List<Game> gameList = gameService.findByHomeTeam(teamHomeName);
        if (gameList.isEmpty()) {
            logger.info("Base is empty. Firstly add some games.");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        logger.info("List of games with name{}",teamHomeName);
        return (ResponseEntity<List<Game>>) gameList;
    }

    @GetMapping("/gameByTeamAway/{teamAwayName}")
    public ResponseEntity<List<Game>> gameListByTeamAway(@PathVariable String teamAwayName){
        List<Game> gameList = gameService.findByHomeAway(teamAwayName);
        if(gameList.isEmpty()){
            logger.info("Base is empty. Firstly add some games");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of games with name{}",teamAwayName);
         return (ResponseEntity<List<Game>>) gameList;
    }

    @GetMapping("/gameById/{game_id}")
    public ResponseEntity findGameByGameId(@PathVariable Long game_id){
        Game game = gameService.findGameById(game_id);
        if(game == null){
            logger.warn("There is no game with given id");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(game,HttpStatus.OK);
    }

    @GetMapping("/gameByDate")
    public ResponseEntity<List<Game>> findFutureGames(){
        List<Game> gameList = gameService.findByFutureGames();
        if(gameList.isEmpty()){
            logger.info("Base is empty. There are no future games.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of games in the future");
        return (ResponseEntity<List<Game>>) gameList;
    }

    @PostMapping("/addNewGame")
    public ResponseEntity<?> addNewGame(@RequestBody Game game){
        logger.info("Adding game : {}",game);
        if(gameService.doesGameExist(game)){
            logger.info("There is this game in database. Game : {}", game);
            return new ResponseEntity<>(String.valueOf(new CustomError("Unable to create Game. Game " +
                    game + " already exists.")), HttpStatus.CONFLICT);
        }
        Long createdGameId = gameService.addGame(game);
        return new ResponseEntity<>(createdGameId, HttpStatus.CREATED);
        }

    @DeleteMapping("/removeGame/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable Long id) {
        boolean isRemoved = gameService.removeGame(id);
        if (!isRemoved) {
            logger.error("Game with Id {}, wasn't removed", id);
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }
        logger.info("Game with id {}, is deleted", id);
        return new ResponseEntity<>(isRemoved, HttpStatus.OK);
    }

    @PutMapping("/editGame/{id}")
    public ResponseEntity<Game> editGame(@RequestBody Game game, @PathVariable Long id) {
        logger.info("Edit game with id {}", id);
        Game game1 = gameService.editGame(id,game);
        if (game1 == null) {
            logger.error("Something went wrong...");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(game, HttpStatus.UPGRADE_REQUIRED);
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



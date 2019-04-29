package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.PlayerEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryGame;
import com.BallInTheNet.Basketball.Models.Game;
import com.BallInTheNet.Basketball.Models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {

    private final RepositoryGame repositoryGame;

    @Autowired
    public GameService(RepositoryGame repositoryGame) {
        this.repositoryGame = repositoryGame;
    }

    MappingService mappingService;

    public GameEntity map(Game game) {

        TeamEntity team = new TeamEntity();

        GameEntity gameEntity = new GameEntity();
        gameEntity.setTeamHomeId(game.getTeamHomeId());
        gameEntity.setTeamAwayId(game.getTeamAwayId());
        gameEntity.setTeamAwayName(game.getTeamHome());
        gameEntity.setTeamAwayName(game.getTeamAway());
        gameEntity.setTeamHomeScore(game.getTeamHomeScore());
        gameEntity.setTeamAwayScore(game.getTeamAwayScore());
        gameEntity.setTeamHomeWin(game.getTeamHomeWin());
        gameEntity.setTeamAwayWin(game.getTeamAwayWin());
        gameEntity.setDate(game.getDate());
        return gameEntity;
    }

    public Game map(GameEntity gameEntity) {
        return new Game(gameEntity.getTeamHomeId(), gameEntity.getTeamAwayId(),gameEntity.getTeamHomeName(), gameEntity.getTeamAwayName(), gameEntity.getTeamHomeScore(), gameEntity.getTeamAwayScore(), gameEntity.getTeamHomeWin(), gameEntity.getTeamAwayWin(), gameEntity.getDate());
    }

    public Long addGame(Game game){
        return repositoryGame.save(map(game)).getGameId();
    }

    public boolean removeGame(Long id) {
        boolean isDeleted = false;
        if (repositoryGame.existsById(id)) {
            repositoryGame.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    public Game editGame(Long id, Game game) {

        if (repositoryGame.existsById(id)) {
            GameEntity newEntity=repositoryGame.findById(id).get();
            newEntity.setTeamHomeId(game.getTeamHomeId());
            newEntity.setTeamAwayId(game.getTeamAwayId());
            newEntity.setTeamHomeName(game.getTeamHome());
            newEntity.setTeamAwayName(game.getTeamAway());
            newEntity.setTeamHomeScore(game.getTeamHomeScore());
            newEntity.setTeamAwayScore(game.getTeamAwayScore());
            newEntity.setTeamHomeWin(game.getTeamHomeWin());
            newEntity.setTeamAwayWin(game.getTeamAwayWin());
            newEntity.setDate(game.getDate());
            repositoryGame.save(newEntity);
            return map(newEntity);
        }
        return null;
    }

    public List<Game> getGames() {
        List<Game> gameList = new ArrayList<>();
        repositoryGame.findAll().forEach(element-> gameList.add(map(element)));
        return gameList;
    }

    public List<Game> findByHomeTeam(String teamHomeName){
        List<Game> gameList = new ArrayList<>();
        repositoryGame.findByTeamHomeName(teamHomeName).forEach(element-> gameList.add(map(element)));
        return gameList;
        //return repositoryGame.findByTeamHome(teamHome).stream().map(this::map).collect(Collectors.toList());
    }

    public List<Game> findByHomeAway(String teamAwayName){
        List<Game> gameList = new ArrayList<>();
        repositoryGame.findByTeamAwayName(teamAwayName).forEach(element-> gameList.add(map(element)));
        return gameList;
        //return repositoryGame.findByTeamAway(teamAway).stream().map(this::map).collect(Collectors.toList());
    }

    public Game findGameById(Long game_id){
        GameEntity gameEntity;
        gameEntity= repositoryGame.findGameByGameId(game_id);
        return map(gameEntity);
    }

    public Boolean doesGameExist(Game game) {
        GameEntity gameEntity = mappingService.map(game);
        return repositoryGame.existsById(gameEntity.getGameId());
    }

    public List<Game> findByFutureGames(){
        List<Game> gameList = new ArrayList<>();
        repositoryGame.findByFutureGames().forEach(element->gameList.add(map(element)));
        return gameList;
    }

}

package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryGame;
import com.BallInTheNet.Basketball.Models.Game;
import com.BallInTheNet.Basketball.Models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {

    private final RepositoryGame repositoryGame;

    @Autowired
    public GameService(RepositoryGame repositoryGame) {
        this.repositoryGame = repositoryGame;
    }

    public GameEntity map(Game game) {

        GameEntity gameEntity = new GameEntity();
        gameEntity.setTeamHome(game.getTeamHome());
        gameEntity.setTeamAway(game.getTeamAway());
        gameEntity.setTeamEntity(null);
        //brakuje listy ustawiona jest na NULL na razie
        gameEntity.setTeamHomeScore(game.getTeamHomeScore());
        gameEntity.setTeamAwayScore(game.getTeamAwayScore());
        gameEntity.setTeamHomeWin(game.getTeamHomeWin());
        gameEntity.setTeamAwayWin(game.getTeamAwayWin());
        gameEntity.setDate(game.getDate());
        return gameEntity;
    }

    public Game map(GameEntity gameEntity) {
        return new Game(gameEntity.getTeamHome(), gameEntity.getTeamAway(), gameEntity.getTeamHomeScore(), gameEntity.getTeamAwayScore(), gameEntity.getTeamHomeWin(), gameEntity.getTeamAwayWin(),null, gameEntity.getDate());
//brakuje listy ustawiona na NULL na razie
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
            newEntity.setTeamHome(game.getTeamHome());
            newEntity.setTeamAway(game.getTeamAway());
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

    public List<Game> findByHomeTeam(String teamHome){
        List<Game> gameList = new ArrayList<>();
        repositoryGame.findByTeamHome(teamHome).forEach(element-> gameList.add(map(element)));
        return gameList;
        //return repositoryGame.findByTeamHome(teamHome).stream().map(this::map).collect(Collectors.toList());
    }

    public List<Game> findByHomeAway(String teamAway){
        List<Game> gameList = new ArrayList<>();
        repositoryGame.findByTeamAway(teamAway).forEach(element-> gameList.add(map(element)));
        return gameList;
        //return repositoryGame.findByTeamAway(teamAway).stream().map(this::map).collect(Collectors.toList());
    }

    public Game findGameById(Long game_id){
        GameEntity gameEntity;
        gameEntity= repositoryGame.findGameByGameId(game_id);
        return map(gameEntity);
    }

    public List<Game> findByFutureGames(){
        List<Game> gameList = new ArrayList<>();
        repositoryGame.findByFutureGames().forEach(element->gameList.add(map(element)));
        return gameList;
    }

}

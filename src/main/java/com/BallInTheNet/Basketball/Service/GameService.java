package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryGame;
import com.BallInTheNet.Basketball.Models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final RepositoryGame repositoryGame;
    private final MappingService mappingService;

    @Autowired
    public GameService(RepositoryGame repositoryGame,MappingService mappingService) {
        this.repositoryGame = repositoryGame;
        this.mappingService = mappingService;
    }

    public Long addGame(Game game){
        return repositoryGame.save(mappingService.map(game)).getGameId();
    }

    public boolean removeGame(Long id) {

        boolean isDeleted = false;
        if (repositoryGame.existsById(id)) {
            repositoryGame.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    public boolean editGame(Long id, Game game) {

        if (repositoryGame.existsById(id) || game != null) {
            GameEntity newEntity=repositoryGame.findById(id).get();
            newEntity.setTeamHome(game.getTeamHome());
            newEntity.setTeamAway(game.getTeamAway());
            newEntity.setTeamHomeScore(game.getTeamHomeScore());
            newEntity.setTeamAwayScore(game.getTeamAwayScore());
            newEntity.setTeamHomeWin(game.getTeamHomeWin());
            newEntity.setTeamAwayWin(game.getTeamAwayWin());
            newEntity.setDate(game.getDate());
            return true;
        }else {
            throw new IllegalArgumentException("Wrong argument");
        }
    }

    public List<Game> getGames() {
        List<Game> gameList = new ArrayList<>();
        repositoryGame.findAll().forEach(element-> gameList.add(mappingService.map(element)));
        return gameList;
    }

    public List<Game> findByHomeTeam(String teamHome){
        List<Game> gameList = new ArrayList<>();
        repositoryGame.findByTeamHome(teamHome).forEach(element-> gameList.add(mappingService.map(element)));
        return gameList;
        //return repositoryGame.findByTeamHome(teamHome).stream().map(this::map).collect(Collectors.toList());
    }

    public List<Game> findByHomeAway(String teamAway){
        List<Game> gameList = new ArrayList<>();
        repositoryGame.findByTeamAway(teamAway).forEach(element-> gameList.add(mappingService.map(element)));
        return gameList;
        //return repositoryGame.findByTeamAway(teamAway).stream().map(this::map).collect(Collectors.toList());
    }

    public List<Game> findByFutureGames(){
        List<Game> gameList = new ArrayList<>();
        repositoryGame.findByFutureGames().forEach(element->gameList.add(mappingService.map(element)));
        return gameList;
    }

}

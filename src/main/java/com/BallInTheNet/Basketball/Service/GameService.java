package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryGame;
import com.BallInTheNet.Basketball.Models.Game;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Game> getGames() {
        List<Game> gameList = new ArrayList<>();
        repositoryGame.findAll().forEach(element-> gameList.add(mappingService.map(element)));
        return gameList;
    }

    public List<Game> findByHomeTeam(String teamHome){
        return repositoryGame.findByTeamHome(teamHome).stream().map(this::map).collect(Collectors.toList());
    }

    public List<Game> findByHomeAway(String teamAway){
        return repositoryGame.findByTeamHome(teamAway).stream().map(this::map).collect(Collectors.toList());
    }

}

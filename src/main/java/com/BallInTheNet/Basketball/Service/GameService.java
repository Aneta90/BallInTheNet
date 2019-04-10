package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryMySQL;
import com.BallInTheNet.Basketball.Models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final
    RepositoryMySQL repositoryMySQL;

    @Autowired
    public GameService(RepositoryMySQL repositoryMySQL) {
        this.repositoryMySQL = repositoryMySQL;
    }


     GameEntity map(Game game) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setTeamHome(game.getTeamHome());
        gameEntity.setTeamAway(game.getTeamAway());
        gameEntity.setTeamEntity(game.getTeam());
        gameEntity.setTeamHomeScore(game.getTeamHomeScore());
        gameEntity.setTeamAwayScore(game.getTeamAwayScore());
        gameEntity.setTeamHomeWine(game.getTeamHomeWin());
        gameEntity.setTeamAwayWine(game.getTeamAwayWine());
        gameEntity.setDate(game.getDate());
        return gameEntity;
    }
     Game map(GameEntity gameEntity) {
        Game game = new Game();
        game.setTeamHome(gameEntity.getTeamHome());
        game.setTeamAway(gameEntity.getTeamAway());
        game.setTeam(gameEntity.getTeamEntity());
        game.setTeamHomeScore(gameEntity.getTeamHomeScore());
        game.setTeamAwayScore(gameEntity.getTeamAwayScore());
        game.setTeamHomeWine(gameEntity.getTeamHomeWin());
        game.setTeamAwayWine(gameEntity.getTeamAwayWine());
        game.setDate(gameEntity.getDate());
        return game;
    }

}

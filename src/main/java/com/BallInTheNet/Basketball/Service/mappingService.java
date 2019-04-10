package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.PlayerEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import com.BallInTheNet.Basketball.Models.Game;
import com.BallInTheNet.Basketball.Models.Player;
import com.BallInTheNet.Basketball.Models.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class mappingService {

    public GameEntity map(Game game) {
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

    public Game map(GameEntity gameEntity) {
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

    public TeamEntity map(Team team) {

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(team.getName());
        teamEntity.setGameEntity(getListOfGame(team));
        teamEntity.setTotalScore(team.getTotalScore());
        return teamEntity;
    }

    public Team map(TeamEntity teamEntity) {

        Team team = new Team();
        team.setName(teamEntity.getName());
        team.setGame(getListOfGame(teamEntity));
        team.setPlayerList();
    }

    public PlayerEntity map(Player player) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setFirstName(player.getFirstName());
        playerEntity.setSurName(player.getSurName());
        playerEntity.setAge(player.getAge());
        playerEntity.setExperience(player.getExperience());
        playerEntity.setInjured(player.getInjured());
        playerEntity.setRating(player.getRating());
        playerEntity.setTeamEntity(player.getTeam());
        return playerEntity;
    }

    public Player map(PlayerEntity playerEntity) {
        Player player = new Player();
        player.setFirstName(playerEntity.getFirstName());
        player.setSurName(playerEntity.getSurName());
        player.setAge(playerEntity.getAge());
        player.setExperience(playerEntity.getExperience());
        player.setInjured(playerEntity.getInjured());
        player.setRating(playerEntity.getRating());
        player.setTeam(playerEntity.getTeamEntity());
        return playerEntity;
    }

    private List<GameEntity> getListOfGame(Team team) {
        List<GameEntity> listOfGameEntity = new ArrayList<>();
        for (int i = 0; i < team.getGame().size(); i++) {
            listOfGameEntity.add(map(team.getGame().get(i)));
        }
        return listOfGameEntity;
    }

    private List<Game> getListOfGame(TeamEntity teamEntity) {
        List<Game> listOfGame = new ArrayList<>();
        for (int i = 0; i < teamEntity.getGameEntity().size(); i++) {
            listOfGame.add(map(teamEntity.getGameEntity().get(i)));
        }
        return listOfGame;
    }

    private List<PlayerEntity> getListOfPlayer(Team team) {
        List<PlayerEntity> listOfPlayerEntity = new ArrayList<>();
        for (int i = 0; i < team.getGame().size(); i++) {
            listOfPlayerEntity.add(map(team.getGame().get(i)));
        }
        return listOfPlayerEntity;
    }
}

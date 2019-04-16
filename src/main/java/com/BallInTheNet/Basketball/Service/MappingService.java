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
public class MappingService {
    //game mapping

    GameEntity map(Game game) {

        TeamEntity team = new TeamEntity();
        team.setName("Wawa");

        GameEntity gameEntity = new GameEntity();
        gameEntity.setTeamHomeId(game.getTeamHomeId());
        gameEntity.setTeamHomeName(game.getTeamHome());
        gameEntity.setTeamAwayName(game.getTeamAway());
        gameEntity.setTeamHomeScore(game.getTeamHomeScore());
        gameEntity.setTeamAwayScore(game.getTeamAwayScore());
        gameEntity.setTeamHomeWin(game.getTeamHomeWin());
        gameEntity.setTeamAwayWin(game.getTeamAwayWin());
        gameEntity.setDate(game.getDate());
        return gameEntity;
    }

    Game map(GameEntity gameEntity) {

        Game game = new Game();
        game.setTeamHomeId(gameEntity.getTeamHomeId());
        game.setTeamAwayId(gameEntity.getTeamAwayId());
        game.setTeamHome(gameEntity.getTeamHomeName());
        game.setTeamAway(gameEntity.getTeamAwayName());
        game.setTeamHomeScore(gameEntity.getTeamHomeScore());
        game.setTeamAwayScore(gameEntity.getTeamAwayScore());
        game.setTeamHomeWin(gameEntity.getTeamHomeWin());
        game.setTeamAwayWin(gameEntity.getTeamAwayWin());
        game.setDate(gameEntity.getDate());
        return game;
    }

    //team mapping
    TeamEntity map(Team team) {

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(team.getName());
        teamEntity.setPlayerEntityList(null);
        teamEntity.setTotalScore(team.getTotalScore());
        return teamEntity;
    }

    Team map(TeamEntity teamEntity) {
        Team team = new Team();
        team.setName(teamEntity.getName());
        team.setPlayerList(null);
        team.setTotalScore(teamEntity.getTotalScore());
        return team;
    }

    //players mapping
    PlayerEntity map(Player player) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setFirstName(player.getFirstName());
        playerEntity.setSurName(player.getSurName());
        playerEntity.setAge(player.getAge());
        playerEntity.setExperience(player.getExperience());
        playerEntity.setInjured(player.getInjured());
        playerEntity.setRating(player.getRating());
        playerEntity.setTeamEntity(map(player.getTeam()));
        return playerEntity;
    }

    Player map(PlayerEntity playerEntity) {
        Player player = new Player();
        player.setFirstName(playerEntity.getFirstName());
        player.setSurName(playerEntity.getSurName());
        player.setAge(playerEntity.getAge());
        player.setExperience(playerEntity.getExperience());
        player.setInjured(playerEntity.getInjured());
        player.setRating(playerEntity.getRating());
        player.setTeam(map(playerEntity.getTeamEntity()));
        player.setTeam(map(playerEntity.getTeamEntity()));
        return player;
    }
}
/*
    //support method
    List<GameEntity> getListOfGame(Team team) {
        List<GameEntity> listOfGameEntity = new ArrayList<>();
        for (int i = 0; i < team().size(); i++) {
            listOfGameEntity.add(map(team.getGame().get(i)));
        }
        return listOfGameEntity;
    }

    List<Game> getListOfGame(TeamEntity teamEntity) {
        List<Game> listOfGame = new ArrayList<>();
        for (int i = 0; i < teamEntity.getGameEntity().size(); i++) {
            listOfGame.add(map(teamEntity.getGameEntity().get(i)));
        }
        return listOfGame;
    }

    List<Player> getListOfPlayers(TeamEntity teamEntity) {
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < teamEntity.getPlayerEntityList().size(); i++) {
            playerList.add(map(teamEntity.getPlayerEntityList().get(i)));
        }
        return playerList;
    }

    List<PlayerEntity> getListOfPlayers(Team team) {
        List<PlayerEntity> playerEntityList = new ArrayList<>();
        for (int i = 0; i < team.getPlayerList().size(); i++) {
            playerEntityList.add(map(team.getPlayerList().get(i)));
        }
        return playerEntityList;
    }

    List<TeamEntity> getListOfTeam(Game game) {
        List<TeamEntity> teamEntityList = new ArrayList<>();
        for (int i = 0; i < game.getTeam().size(); i++) {
            teamEntityList.add(map(game.getTeam().get(i)));
        }
        return teamEntityList;
    }

    List<Team> getListOfTeam(GameEntity gameEntity) {
        List<Team> teamList = new ArrayList<>();
        for (int i = 0; i < gameEntity.getTeamEntity().size(); i++) {
            teamList.add(map(gameEntity.getTeamEntity().get(i)));
        }
        return teamList;
    }*/


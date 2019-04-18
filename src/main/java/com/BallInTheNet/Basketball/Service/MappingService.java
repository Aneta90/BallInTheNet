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
        GameEntity gameEntity = new GameEntity();
        gameEntity.setTeamHomeId(game.getTeamHomeId());
        gameEntity.setTeamAwayId(game.getTeamAwayId());
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
//        List <Player> playerList = new ArrayList<>();
//        for (int i = 0; i <teamEntity.getPlayerEntityList().size() ; i++) {
//            playerList.add(map(teamEntity.getPlayerEntityList().get(i)));
//        }
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

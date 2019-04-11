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


    public GameEntity map(Game game) {

        TeamEntity team = new TeamEntity();
        team.setName("Wawa");

        GameEntity gameEntity = new GameEntity();
        gameEntity.setTeamHome(game.getTeamHome());
        gameEntity.setTeamAway(game.getTeamAway());
        gameEntity.getTeamEntity().add(team);
        gameEntity.setTeamHomeScore(game.getTeamHomeScore());
        gameEntity.setTeamAwayScore(game.getTeamAwayScore());
        gameEntity.setTeamHomeWin(game.getTeamHomeWin());
        gameEntity.setTeamAwayWin(game.getTeamAwayWin());
        gameEntity.setDate(game.getDate());
        return gameEntity;
    }

    public Game map(GameEntity gameEntity) {

        Team team = new Team();
        team.setName("Cracovia");

        Game game = new Game();
        game.setTeamHome(gameEntity.getTeamHome());
        game.setTeamAway(gameEntity.getTeamAway());
        game.getTeam().add(team);
        game.setTeamHomeScore(gameEntity.getTeamHomeScore());
        game.setTeamAwayScore(gameEntity.getTeamAwayScore());
        game.setTeamHomeWin(gameEntity.getTeamHomeWin());
        game.setTeamAwayWin(gameEntity.getTeamAwayWin());
        game.setDate(gameEntity.getDate());
        return game;
    }

    //team mapping
    public TeamEntity map(Team team) {

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(team.getName());
        teamEntity.setGameEntity(getListOfGame(team));
        teamEntity.setPlayerEntityList(getListOfPlayers(team));
        teamEntity.setTotalScore(team.getTotalScore());
        return teamEntity;
    }

    public Team map(TeamEntity teamEntity) {
        Team team = new Team();
        team.setName(teamEntity.getName());
        team.setGame(getListOfGame(teamEntity));
        team.setPlayerList(getListOfPlayers(teamEntity));
        team.setTotalScore(teamEntity.getTotalScore());
        return team;
    }

    //players mapping
    public PlayerEntity map(Player player) {
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

    public Player map(PlayerEntity playerEntity) {
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


    //support method
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

    private List<Player> getListOfPlayers(TeamEntity teamEntity) {
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < teamEntity.getPlayerEntityList().size(); i++) {
            playerList.add(map(teamEntity.getPlayerEntityList().get(i)));
        }
        return playerList;
    }

    private List<PlayerEntity> getListOfPlayers(Team team) {
        List<PlayerEntity> playerEntityList = new ArrayList<>();
        for (int i = 0; i < team.getPlayerList().size(); i++) {
            playerEntityList.add(map(team.getPlayerList().get(i)));
        }
        return playerEntityList;
    }

    private List<TeamEntity> getListOfTeam(Game game) {
        List<TeamEntity> teamEntityList = new ArrayList<>();
        for (int i = 0; i < game.getTeam().size(); i++) {
            teamEntityList.add(map(game.getTeam().get(i)));
        }
        return teamEntityList;
    }

    private List<Team> getListOfTeam(GameEntity gameEntity) {
        List<Team> teamList = new ArrayList<>();
        for (int i = 0; i < gameEntity.getTeamEntity().size(); i++) {
            teamList.add(map(gameEntity.getTeamEntity().get(i)));
        }
        return teamList;
    }
}

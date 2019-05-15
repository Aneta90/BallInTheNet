package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.PlayerEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryGame;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryPlayer;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryTeam;
import com.BallInTheNet.Basketball.Models.Game;
import com.BallInTheNet.Basketball.Models.Player;
import com.BallInTheNet.Basketball.Models.Team;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(MockitoJUnitRunner.class)
public class MappingServiceTest {

    @Mock
    RepositoryTeam repositoryTeam;

    @Mock
    RepositoryPlayer repositoryPlayer;

    @Mock
    RepositoryGame repositoryGame;

    @InjectMocks
    MappingService mappingService;

    private PlayerEntity playerEntity1;
    private Player player1;
    private PlayerEntity playerEntity2;
    private Player player2;
    private TeamEntity teamEntity;
    private Team team;
    private TeamEntity teamEntity1;
    private Team team1;
    private Game game;
    private GameEntity gameEntity1;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mappingService = new MappingService();

        teamEntity = new TeamEntity();
        teamEntity.setName("TeamEntity");
        teamEntity.setTotalScore(123L);

        team = new Team();
        team.setName("TeamEntity");
        team.setTotalScore(123L);


        teamEntity1 = new TeamEntity();
        teamEntity1.setName("TeamEntity");
        teamEntity1.setTotalScore(123L);

        team1 = new Team();
        team1.setName("TeamEntity");
        team1.setTotalScore(123L);

        playerEntity1 = new PlayerEntity();
        playerEntity1.setFirstName("John");
        playerEntity1.setSurName("Nowak");
        playerEntity1.setAge(25);
        playerEntity1.setTeamEntity(teamEntity);
        playerEntity1.setRating(99);
        playerEntity1.setInjured(false);
        playerEntity1.setExperience(3);

        player1 = new Player();
        player1.setFirstName("John");
        player1.setSurName("Nowak");
        player1.setAge(25);
        player1.setTeam(team);
        player1.setRating(99);
        player1.setInjured(false);
        player1.setExperience(3);

        playerEntity2 = new PlayerEntity();
        playerEntity2.setFirstName("John");
        playerEntity2.setSurName("Nowak");
        playerEntity2.setAge(25);
        playerEntity2.setTeamEntity(teamEntity);
        playerEntity2.setRating(99);
        playerEntity2.setInjured(false);
        playerEntity2.setExperience(3);

        player2 = new Player();
        player2.setFirstName("John");
        player2.setSurName("Nowak");
        player2.setAge(25);
        player2.setTeam(team);
        player2.setRating(99);
        player2.setInjured(false);
        player2.setExperience(3);

        List<PlayerEntity> playerEntityList = new ArrayList<>();
        playerEntityList.add(playerEntity1);
        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);

        List<PlayerEntity> playerEntityList2 = new ArrayList<>();
        playerEntityList2.add(playerEntity1);
        List<Player> playerList2 = new ArrayList<>();
        playerList2.add(player1);

        teamEntity.setPlayerEntityList(playerEntityList);
        team.setPlayerList(playerList);
        repositoryTeam.save(teamEntity);
        repositoryPlayer.save(playerEntity1);

        teamEntity1.setPlayerEntityList(playerEntityList2);
        team1.setPlayerList(playerList2);
        repositoryTeam.save(teamEntity1);
        repositoryPlayer.save(playerEntity2);

        game = new Game();
        game.setTeamHomeId(1L);
        game.setTeamAwayId(2L);
        game.setTeamHome("Cracow");
        game.setTeamAway("Warsaw");
        game.setDate(LocalDate.of(2009,5,5));
        game.setTeamHomeWin(true);
        game.setTeamAwayWin(false);
        game.setTeamHomeScore(50);
        game.setTeamAwayScore(40);


        gameEntity1 = new GameEntity();
        gameEntity1.setTeamHomeId(1L);
        gameEntity1.setTeamAwayId(2L);
        gameEntity1.setTeamHomeName("Cracow");
        gameEntity1.setTeamAwayName("Warsaw");
        gameEntity1.setDate(LocalDate.of(2009,5,5));
        gameEntity1.setTeamHomeWin(true);
        gameEntity1.setTeamAwayWin(false);
        gameEntity1.setTeamHomeScore(50);
        gameEntity1.setTeamAwayScore(40);

        repositoryGame.save(gameEntity1);


    }

    @Test
    public void mapPlayerEntityToPlayer() {
        Player playerFromMapping = mappingService.map(playerEntity1);
        assertEquals(playerFromMapping.getSurName(), player1.getSurName());
        assertEquals(playerFromMapping.getTeam().getName(), player1.getTeam().getName());
        assertEquals(playerFromMapping.getAge(), player1.getAge());
        assertEquals(playerFromMapping.getRating(), player1.getRating());
    }

    @Test
    public void mapPlayerToPlayerEntity() {

        PlayerEntity playerEntityFromMapping = mappingService.map(player1);
        assertEquals(playerEntityFromMapping.getFirstName(),playerEntity1.getFirstName());
    }

    @Test
    public void mapTeamEntityToTeam() {
        Team teamFromMapping = mappingService.map(teamEntity1);
        assertEquals(teamFromMapping.getName(), team1.getName());
    }

    @Test
    public void mapTeamToTeamEntity() {
        TeamEntity teamEntityFromMapping = mappingService.map(team1);
        assertEquals(teamEntityFromMapping.getTotalScore(),teamEntity1.getTotalScore());
    }

    @Test
    public void mapGameEntityToGame() {

        Game gameFromMapping = mappingService.map(gameEntity1);
        assertEquals(gameFromMapping.getTeamAway(), gameEntity1.getTeamAwayName());
    }

    @Test
    public void mapGameToGameEntity() {
        GameEntity gameEntityFromMapping = mappingService.map(game);
        assertEquals(gameEntityFromMapping.getTeamHomeScore(),gameEntity1.getTeamHomeScore());
    }

}
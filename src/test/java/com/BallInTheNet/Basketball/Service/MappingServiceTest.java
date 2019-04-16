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
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(MockitoJUnitRunner.Silent.class)
@ActiveProfiles("test")
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

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this); // inicjalizuje mocki z tej klasy (this)
        mappingService = new MappingService(); // wstrzykuje do naszego servicu repozytorium mokowe z gory z tej klasy


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
        playerEntity1.setTeamEntity(teamEntity); //to change
        playerEntity1.setRating(99);
        playerEntity1.setInjured(false);
        playerEntity1.setExperience(3);

        player1 = new Player();
        player1.setFirstName("John");
        player1.setSurName("Nowak");
        player1.setAge(25);
        player1.setTeam(team); //to change
        player1.setRating(99);
        player1.setInjured(false);
        player1.setExperience(3);

        playerEntity2 = new PlayerEntity();
        playerEntity2.setFirstName("John");
        playerEntity2.setSurName("Nowak");
        playerEntity2.setAge(25);
        playerEntity2.setTeamEntity(teamEntity); //to change
        playerEntity2.setRating(99);
        playerEntity2.setInjured(false);
        playerEntity2.setExperience(3);

        player2 = new Player();
        player2.setFirstName("John");
        player2.setSurName("Nowak");
        player2.setAge(25);
        player2.setTeam(team); //to change
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

        List <GameEntity> gameEntityList = new ArrayList<>();
        List<Game> gamesList = new ArrayList<>();

        teamEntity.setPlayerEntityList(playerEntityList);
        team.setPlayerList(playerList);
        repositoryTeam.save(teamEntity);
        repositoryPlayer.save(playerEntity1);


        teamEntity1.setPlayerEntityList(playerEntityList2);
        team1.setPlayerList(playerList2);
        repositoryTeam.save(teamEntity1);
        repositoryPlayer.save(playerEntity2);


    }

    @Test
    public void mapPlayerEntityToPlayer() {
        Player playerFromMapping = mappingService.map(playerEntity1);
        assertEquals(playerFromMapping, player1);
    }

    @Test
    public void mapPlayerToPlayerEntity() {
    }

    @Test
    public void mapTeamEntityToTeam() {
    }

    @Test
    public void mapTeamToTeamEntity() {
    }

    @Test
    public void mapGameEntityToGame() {
    }

    @Test
    public void mapGameToGameEntity() {
    }


    //supportMethod
    @Test
    public void getListOfGame() {
    }

    @Test
    public void getListOfGame1() {
    }

    @Test
    public void getListOfPlayers() {
    }

    @Test
    public void getListOfPlayers1() {
    }

    @Test
    public void getListOfTeam() {
    }

    @Test
    public void getListOfTeam1() {
    }
}
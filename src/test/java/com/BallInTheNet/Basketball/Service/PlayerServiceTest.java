package com.BallInTheNet.Basketball.Service;


import com.BallInTheNet.Basketball.Domain.EntityModels.PlayerEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryPlayer;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryTeam;
import com.BallInTheNet.Basketball.Models.Player;
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
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

    @Mock
    RepositoryPlayer repositoryPlayer;

    @Mock
    RepositoryTeam repositoryTeam;

    @Mock
    MappingService mappingService;

    @Mock
    TeamService teamService;

    @InjectMocks
    PlayerService playerService;

    private List<PlayerEntity> listOfAllPlayersEntity = new ArrayList<>();
    private List<PlayerEntity> listOfPlayerEntityNameJohn = new ArrayList<>();
    private List<Player> listOfAllPlayerMapped = new ArrayList<>();
    private List<Player> listOfPlayerNameJohn = new ArrayList<>();
    private List<PlayerEntity> listOfInjuredPlayersEntity = new ArrayList<>();
    private List<Player> listOfInjuredPlayers = new ArrayList<>();
    private List<PlayerEntity> listOfPlayerEntityOlderThen = new ArrayList<>();
    private List<Player> listOfPlayerOlderThen = new ArrayList<>();
    private List<PlayerEntity> listOfPlayerEntityYoungerThen = new ArrayList<>();
    private List<Player> listOfPlayerYoungerThen = new ArrayList<>();

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        playerService = new PlayerService(mappingService, repositoryPlayer, repositoryTeam, teamService);
        PlayerEntity playerEntity1 = new PlayerEntity();
        playerEntity1.setFirstName("John");
        playerEntity1.setSurName("Nowak");
        playerEntity1.setAge(25);
        playerEntity1.setTeamEntity(null); //to change
        playerEntity1.setRating(99);
        playerEntity1.setInjured(false);
        playerEntity1.setExperience(3);

        PlayerEntity playerEntity2 = new PlayerEntity();
        playerEntity2.setFirstName("Tom");
        playerEntity2.setSurName("Kowalsky");
        playerEntity2.setAge(30);
        playerEntity2.setTeamEntity(null); //to change
        playerEntity2.setRating(80);
        playerEntity2.setInjured(true);
        playerEntity2.setExperience(10);

        Player player1 = new Player();
        player1.setFirstName("John");
        player1.setSurName("Nowak");
        player1.setAge(25);
        player1.setTeam(null); //to change
        player1.setRating(99);
        player1.setInjured(false);
        player1.setExperience(3);

        Player player2 = new Player();
        player2.setFirstName("Tom");
        player2.setSurName("Kowalsky");
        player2.setAge(30);
        player2.setTeam(null); //to change
        player2.setRating(80);
        player2.setInjured(true);
        player2.setExperience(10);

        listOfAllPlayersEntity.add(playerEntity1);
        listOfAllPlayersEntity.add(playerEntity2);
        listOfPlayerEntityNameJohn.add(playerEntity1);
        listOfInjuredPlayersEntity.add(playerEntity2);
        listOfPlayerEntityOlderThen.add(playerEntity2);
        listOfAllPlayerMapped.add(player1);
        listOfAllPlayerMapped.add(player2);
        listOfPlayerNameJohn.add(player1);
        listOfInjuredPlayers.add(player2);
        listOfPlayerOlderThen.add(player2);
        listOfPlayerYoungerThen.add(player1);
        listOfPlayerEntityYoungerThen.add(playerEntity1);
        when(mappingService.map(listOfInjuredPlayersEntity.get(0))).thenReturn(listOfInjuredPlayers.get(0));
        when(repositoryPlayer.findAll()).thenReturn(listOfAllPlayersEntity);
        when(repositoryPlayer.findAllBySurName(listOfPlayerEntityNameJohn.get(0)
                .getSurName())).thenReturn(listOfPlayerEntityNameJohn);
        when(mappingService.map(listOfPlayerEntityNameJohn.get(0))).thenReturn(listOfPlayerNameJohn.get(0));
        when(mappingService.map(listOfPlayerEntityOlderThen.get(0))).thenReturn(listOfPlayerOlderThen.get(0));
        when(mappingService.map(listOfPlayerEntityYoungerThen.get(0))).thenReturn(listOfPlayerYoungerThen.get(0));
        when(repositoryPlayer.existsById(listOfPlayerEntityNameJohn.get(0).getPlayerId())).thenReturn(true);
        when(repositoryPlayer.getOne(listOfPlayerEntityNameJohn.get(0).getPlayerId()))
                .thenReturn(listOfPlayerEntityNameJohn.get(0));

    }

    @Test
    public void getListOfPlayer() {
        List<Player> playerList = playerService.getListOfPlayer();
        assertNotEquals(playerList.size(), 0);
        assertEquals("Nowak", playerList.get(0).getSurName());
    }

    @Test
    public void findAllPlayersInTeam() {
    }

    @Test
    public void playersWithGivenName() {
        List<Player> playerList = playerService.playersWithGivenName(listOfPlayerEntityNameJohn.get(0).getSurName());
        assertEquals(playerList.size(), 1);
    }

    @Test
    public void findOlderThen() {
        int age = 29;
        when(repositoryPlayer.findAllByAgeAfter(age)).thenReturn(listOfPlayerEntityOlderThen);
        List<Player> playerList = playerService.findOlderThen(age);

        assertEquals(1, playerList.size());
    }

    @Test
    public void findYoungerThen() {
        int age = 29;
        when(repositoryPlayer.findAllByAgeBefore(age)).thenReturn(listOfPlayerEntityYoungerThen);
        List<Player> playerList = playerService.findYoungerThen(age);
        assertEquals(1, playerList.size());

    }

    @Test
    public void listOfInjuredPlayers() {
        when(repositoryPlayer.findAllByIsInjuredTrue()).thenReturn(listOfInjuredPlayersEntity);
        List<Player> playerList = playerService.listOfInjuredPlayers();
        assertNotEquals(playerList.size(), 0);
    }

    @Test
    public void editPlayer() {
        Player player = new Player();
        player.setFirstName("qwe");
        player.setSurName("qwerty");
        player.setAge(45);
        player.setExperience(5);
        player.setInjured(false);
        player.setRating(70);
        player.setTeam(null);
        when(mappingService.map(listOfPlayerEntityNameJohn.get(0))).thenReturn(player);
        Player player1 = playerService.editPlayer(player, listOfPlayerEntityNameJohn.get(0).getPlayerId());
        assertEquals("qwerty", player1.getSurName());
    }
}

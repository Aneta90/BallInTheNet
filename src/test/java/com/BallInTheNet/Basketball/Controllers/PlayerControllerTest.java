package com.BallInTheNet.Basketball.Controllers;

import com.BallInTheNet.Basketball.Domain.EntityModels.PlayerEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryPlayer;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryTeam;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayerControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RepositoryPlayer repositoryPlayer;

    @Autowired
    private RepositoryTeam repositoryTeam;

    private PlayerEntity playerEntity = new PlayerEntity();
    private TeamEntity teamEntity = new TeamEntity();

    @Before
    public void setUp() {

        List<PlayerEntity> playerEntityList = new ArrayList<>();
        playerEntity.setFirstName("Anett");
        playerEntity.setSurName("Wrobel");
        playerEntity.setExperience(10);
        playerEntity.setInjured(false);
        playerEntity.setTeamEntity(teamEntity);
        playerEntity.setRating(100);
        playerEntity.setAge(25);
        playerEntityList.add(playerEntity);
        repositoryTeam.save(teamEntity);
        repositoryPlayer.save(playerEntity);

        teamEntity.setName("Warsaw");
        teamEntity.setPlayerEntityList(playerEntityList);
        teamEntity.setTotalScore(100L);

    }

    @Test
    public void playersList() {
        ResponseEntity<PlayerEntity[]> response = testRestTemplate.getForEntity(
                "/players/playersList", PlayerEntity[].class
        );
        List<PlayerEntity> list = repositoryPlayer.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Long.valueOf(list.get(0).getExperience()), Long.valueOf(10));
    }

    @Test
    public void playersWithGivenName() {
        ResponseEntity<PlayerEntity[]> response = testRestTemplate.getForEntity(
                "/players/playerBySurName/Wrobel", PlayerEntity[].class
        );
        List<PlayerEntity> list = repositoryPlayer.findAllBySurName("Wrobel");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.get(0).getFirstName(), "Anett");
    }

    @Test
    public void playersOlderThen() {
        ResponseEntity<PlayerEntity[]> response = testRestTemplate.getForEntity(
                "/players/findOlderThen/25", PlayerEntity[].class
        );
        List<PlayerEntity> list = repositoryPlayer.findAllByAgeAfter(25);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        assertTrue(list.isEmpty());
    }

    @Test
    public void playersYoungerThen() {
        ResponseEntity<PlayerEntity[]> response = testRestTemplate.getForEntity(
                "/players/findYoungerThen/26", PlayerEntity[].class
        );
        List<PlayerEntity> list = repositoryPlayer.findAllByAgeBefore(26);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(25, (int) list.get(0).getAge());
    }

    @Test
    public void injuredPlayersList() {
        ResponseEntity<PlayerEntity[]> response = testRestTemplate.getForEntity(
                "/players/injuredPlayers", PlayerEntity[].class
        );
        List<PlayerEntity> list = repositoryPlayer.findAllByIsInjuredTrue();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(list.isEmpty());
    }
}
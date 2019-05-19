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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    RepositoryTeam repositoryTeam;

    @Autowired
    RepositoryPlayer repositoryPlayer;

    private TeamEntity teamEntity = new TeamEntity();

    @Before
    public void init() {

        teamEntity.setName("Warsaw");
        teamEntity.setTotalScore(100L);

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setFirstName("Aneta");
        playerEntity.setSurName("Wrobel");
        playerEntity.setAge(20);
        playerEntity.setRating(2000);
        playerEntity.setTeamEntity(teamEntity);
        playerEntity.setInjured(true);
        playerEntity.setExperience(12);

        Long id = teamEntity.getTeamId();
        repositoryTeam.save(teamEntity);
        repositoryPlayer.save(playerEntity);

    }

    @Test
    public void getTeamsTest() {

        ResponseEntity<TeamEntity[]> response = testRestTemplate.getForEntity(
                "/team/teamList", TeamEntity[].class
        );

        List<TeamEntity> list = repositoryTeam.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.get(0).getTotalScore(), Long.valueOf(100L));

    }

    @Test
    public void getTeamByTotalScore() {

        ResponseEntity<TeamEntity[]> response = testRestTemplate.getForEntity(
                "/team/teamByTotalScoreBiggerThen/100", TeamEntity[].class
        );

        List<TeamEntity> list = repositoryTeam.findByTotalScore(100L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(list.get(0).getName(), "Warsaw");

    }

    @Test
    public void editGameTest() {

        Map<String, String> params = new HashMap<>();
        params.put("id", "1");

        Long id = teamEntity.getTeamId();
        String url = "http://localhost:8080/team/editTeam/{id}";
        teamEntity.setName("Warsawwwa");

        testRestTemplate.put(url, teamEntity, params);
        repositoryTeam.findById(id);
        assertEquals(teamEntity.getName(), "Warsawwwa");
    }
}


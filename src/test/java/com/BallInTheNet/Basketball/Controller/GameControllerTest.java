package com.BallInTheNet.Basketball.Controller;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryGame;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.GET;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerTest {


    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RepositoryGame repositoryGame;

    Long id;

    @Before
    public void init(){

        GameEntity gameEntity1 = new GameEntity();
        gameEntity1.setTeamHomeId(1L);
        gameEntity1.setTeamAwayId(2L);
        gameEntity1.setTeamHomeName("Warsaw");
        gameEntity1.setTeamAwayName("Cracow");
        gameEntity1.setTeamAwayWin(true);
        gameEntity1.setTeamHomeWin(false);
        gameEntity1.setTeamAwayScore(5);
        gameEntity1.setTeamHomeScore(2);
        gameEntity1.setDate(LocalDate.of(2020,4,3));
        repositoryGame.save(gameEntity1);
        id= gameEntity1.getGameId();
    }


    @Test
    public void getGames(){

        ResponseEntity<GameEntity[]> response = testRestTemplate.getForEntity(
                "/game/gameByDate",GameEntity[].class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    }



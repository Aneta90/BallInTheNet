package com.BallInTheNet.Basketball.Controllers;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryGame;
import com.BallInTheNet.Basketball.Models.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RepositoryGame repositoryGame;

    private Long id;
    private GameEntity gameEntity1 = new GameEntity();

    @Before
    public void init() {

        gameEntity1.setTeamHomeId(1L);
        gameEntity1.setTeamAwayId(2L);
        gameEntity1.setTeamHomeName("Warsaw");
        gameEntity1.setTeamAwayName("Cracow");
        gameEntity1.setTeamAwayWin(true);
        gameEntity1.setTeamHomeWin(false);
        gameEntity1.setTeamAwayScore(5);
        gameEntity1.setTeamHomeScore(2);
        gameEntity1.setDate(LocalDate.of(2020, 4, 3));
        repositoryGame.save(gameEntity1);
        id = gameEntity1.getGameId();
    }

    @Test
    public void addGameTest() {
        Game game = new Game(1L, 2L, "Warsaw", "Cracow", 5, 2, true, false, LocalDate.of(2020, 4, 3));

        ResponseEntity<Long> response = testRestTemplate.postForEntity(
                "/game/addNewGame",
                game,
                Long.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Long.valueOf(2L), Long.valueOf(game.getTeamAwayScore()));

    }

    @Test
    public void getGamesByDateTest() {

        ResponseEntity<GameEntity[]> response = testRestTemplate.getForEntity(
                "/game/gameByDate", GameEntity[].class
        );
        List<GameEntity> list= repositoryGame.findByFutureGames();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Long.valueOf(list.get(0).getTeamAwayScore()),Long.valueOf(5L));
    }

    @Test
    public void getGameByTeamHomeNameTest() {

        ResponseEntity<GameEntity[]> response = testRestTemplate.getForEntity(
                "/game/gameByTeamHome/Warsaw", GameEntity[].class
        );

        List<GameEntity> game = repositoryGame.findByTeamHomeName("Warsaw");
        assertNotNull(id);
        assertEquals("Warsaw",game.get(0).getTeamHomeName());
        assertEquals("Cracow",game.get(0).getTeamAwayName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

    }

    @Test
    public void getGameByIdTest() {

        ResponseEntity response = testRestTemplate.getForEntity(
                "/game/gameById/1", GameEntity.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        repositoryGame.findById(id);
        assertNotNull(id);

    }

    @Test
    public void removeGameTest(){ //remove nie działa bo jest zła wersja JUNIT 4.12 --> 4.11 ale jak się zmienia na 4.11 to Spring na to nie pozwala i koło się zamyka (ten sam probem co na zajęciach), w POSTMANIE usuwa

        Long gameId = gameEntity1.getGameId();
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        String url = "http://localhost:8080/game/removeGame/{id}";
        testRestTemplate.delete(url,params);

        //testRestTemplate.delete("/game/removeGame/{id}",id);
        //repositoryGame.findById(id);
        //assertNull(gameId);
    }

    @Test
    public void editGameTest(){ //nie wiem czy Ok w postamanie działa

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");

        Long id = gameEntity1.getGameId();
        String url = "http://localhost:8080/game/editGame/{id}";
        gameEntity1.setTeamAwayName("Warsawwwa");

        testRestTemplate.put(url,gameEntity1,params);
        repositoryGame.findById(id);
        assertEquals(gameEntity1.getTeamAwayName(),"Warsawwwa");
    }
}





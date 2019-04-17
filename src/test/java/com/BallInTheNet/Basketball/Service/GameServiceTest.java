package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryGame;
import com.BallInTheNet.Basketball.Models.Game;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
public class GameServiceTest {

    @Mock
    RepositoryGame repositoryGame;

    private GameService gameService;

    private List<GameEntity> gameEntityList  = new ArrayList();

    @Before
    public void init(){

        MockitoAnnotations.initMocks(this);
        gameService = new GameService(repositoryGame);

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

        gameEntityList.add(gameEntity1);

        when(repositoryGame.findGameByGameId(1L)).thenReturn(gameEntity1);
        when(repositoryGame.findByFutureGames()).thenReturn(gameEntityList);
        when(repositoryGame.findByTeamAwayName("Cracow")).thenReturn(gameEntityList);
        when(repositoryGame.findByTeamHomeName("Warsaw")).thenReturn(gameEntityList);

    }

    @Test
    public void gameServiceFindByIdTest(){

        Game game = gameService.findGameById(1L);
        assertNotNull(game);
        assertEquals(game.getTeamHome(),"Warsaw");

    }

    @Test
    public void whenFindByTeamAwayNameThenReturnGame(){
        List<Game> gameEntityList = gameService.findByHomeAway("Cracow");
        assertNotNull(gameEntityList);
        assertEquals(gameEntityList.get(0).getDate(),LocalDate.of(2020,4,3));
    }

    @Test
    public void whenFindByTeamHomeNameThenReturnGame(){
        List<Game> gameEntityList = gameService.findByHomeTeam("Warsaw");
        assertNotNull(gameEntityList);
        assertEquals(gameEntityList.get(0).getDate(),LocalDate.of(2020,4,3));
    }

    @Test
    public void whenFindByFutureGamesThenReturnGame(){
        List<Game> gameEntityList = gameService.findByFutureGames();
        assertNotNull(gameEntityList);
        assertEquals(gameEntityList.get(0).getDate(),LocalDate.of(2020,4,3));
    }


}

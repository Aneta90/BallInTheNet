package com.BallInTheNet.Basketball.Repository;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryGame;
import com.BallInTheNet.Basketball.Models.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class GameRepositoryTest {

    @Autowired
    private RepositoryGame gameRepository;

    @Before
    public void setUp(){

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
        gameRepository.save(gameEntity1);

    }

    @Test
    public void whenFindByFutureGames_thenReturnGame(){
        List<GameEntity> gameEntity = gameRepository.findByFutureGames();
        assertNotNull(gameEntity);
        assertEquals(gameEntity.get(0).getDate(), LocalDate.of(2020,4,3));
        assertEquals(gameEntity.size(),1);
    }

    @Test
    public void whenFindByTeamHomeName_thenReturnGame(){
        List<GameEntity> gameEntity = gameRepository.findByTeamHomeName("Warsaw");
        assertNotNull(gameEntity);
        assertEquals(gameEntity.get(0).getTeamHomeName(),"Warsaw");
    }

    @Test
    public void whenFindByTeamAwayName_thenReturnNull(){

        List<GameEntity> gameEntity = gameRepository.findByTeamAwayName("Warsaw");
        assertTrue(gameEntity.isEmpty());
    }

    @Test
    public void whenFindGameByIdThenReturnGame(){

        GameEntity game = gameRepository.findGameByGameId(1L);
        assertNotNull(game.getGameId());
        assertEquals(Optional.of(game.getGameId()),Optional.of(1L));

    }
    @Test
    public void whenFindGameByIdThenReturnNull(){

        Optional<GameEntity> game = Optional.ofNullable(gameRepository.findGameByGameId(3L));
        assertEquals(Optional.empty(), game);
    }
}

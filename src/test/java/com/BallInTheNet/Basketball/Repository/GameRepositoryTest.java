package com.BallInTheNet.Basketball.Repository;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryGame;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class GameRepositoryTest {

    @Autowired
    private RepositoryGame gameRepository;

    @Before
    public void setUp(){

        GameEntity gameEntity = new GameEntity();
        gameEntity.setTeamHomeId(1L);
        gameEntity.setTeamAwayId(2L);
        gameEntity.setTeamHomeName("Warsaw");
        gameEntity.setTeamAwayName("Cracow");
        gameEntity.setTeamAwayWin(true);
        gameEntity.setTeamHomeWin(false);
        gameEntity.setTeamAwayScore(5);
        gameEntity.setTeamHomeScore(2);
        gameEntity.setDate(LocalDate.of(2019,4,16));
        gameRepository.save(gameEntity);

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

}

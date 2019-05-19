package com.BallInTheNet.Basketball.Repository;

import com.BallInTheNet.Basketball.Domain.EntityModels.PlayerEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryPlayer;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryTeam;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlayerRepositoryTest {


    @Autowired
    private RepositoryPlayer playerRepository;

    @Autowired
    private RepositoryTeam repositoryTeam;

    Long teamId;

    @Before
    public void setUp() {

        List<PlayerEntity> playerList = new ArrayList<>();

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName("Warsaw");
        teamEntity.setPlayerEntityList(playerList);
        teamEntity.setTotalScore(100L);

        repositoryTeam.save(teamEntity);
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setFirstName("Aneta");
        playerEntity.setSurName("Wrobel");
        playerEntity.setAge(30);
        playerEntity.setRating(100);
        playerEntity.setTeamEntity(teamEntity);

        teamId = teamEntity.getTeamId();

        playerList.add(playerEntity);
        playerRepository.save(playerEntity);

    }

    @Test
    public void whenFindBySurname_thenReturnListOfPlayers() {
        List<PlayerEntity> playerEntityList = playerRepository.findAllBySurName("Wrobel");
        assertNotNull(playerEntityList);
        assertEquals(playerEntityList.get(0).getFirstName(), "Aneta");
        assertEquals(playerEntityList.size(), 1);
    }

    @Test
    public void whenFindByTeamId_thenReturnPlayers() {
        List<PlayerEntity> playerEntityList = playerRepository.findAllPlayersByTeamId(teamId);
        assertNotNull(playerEntityList);
        assertEquals(playerEntityList.get(0).getFirstName(), "Aneta");
        assertEquals(playerEntityList.size(), 1);
    }

    @Test
    public void whenFindByAge_thenReturnOlderPlayers() {
        List<PlayerEntity> playerEntity = playerRepository.findAllByAgeAfter(30);
        assertTrue(playerEntity.isEmpty());
    }

    @Test
    public void whenFindByAge_thenReturnYoungerThan() {
        List<PlayerEntity> playerEntityList = playerRepository.findAllByAgeBefore(31);
        assertEquals(playerEntityList.size(), 1);
        assertEquals(Long.valueOf(playerEntityList.get(0).getAge()),Long.valueOf(30));
    }


}

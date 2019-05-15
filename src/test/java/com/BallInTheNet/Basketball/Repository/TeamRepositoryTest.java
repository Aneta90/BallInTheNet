package com.BallInTheNet.Basketball.Repository;

import com.BallInTheNet.Basketball.Domain.EntityModels.PlayerEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryTeam;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeamRepositoryTest {

    @Autowired
    private RepositoryTeam teamRepository;

    @Before
    public void setUp() {
        TeamEntity teamEntity = new TeamEntity();

        List<PlayerEntity> playerList = new ArrayList<>();
        playerList.add(new PlayerEntity("Aneta", "Wrobel", 30, 30, true, 100, teamEntity));

        teamEntity.setName("Warsaw");
        teamEntity.setPlayerEntityList(playerList);
        teamEntity.setTotalScore(100L);
        teamRepository.save(teamEntity);

    }

    @Test
    public void whenFindByName_thenReturnListOfTeams() {
        List<TeamEntity> teamEntity = teamRepository.findByName("Warsaw");
        assertNotNull(teamEntity);
        assertEquals(Optional.ofNullable(teamEntity.get(0).getTotalScore()), Optional.of(100L));
        assertEquals(teamEntity.size(), 1);
    }

    @Test
    public void whenFindByName_thenReturnTeam() {
        TeamEntity teamEntity;
        List<TeamEntity> teamEntityList = teamRepository.findByName("Warsaw");
        teamEntity = teamEntityList.get(0);
        assertNotNull(teamEntity);
        assertEquals(teamEntity.getName(), "Warsaw");
        assertEquals(teamEntityList.size(), 1);
    }

    @Test
    public void whenFindByTotalScore_thenReturnTeam() {
        List<TeamEntity> teamEntity = teamRepository.findByTotalScore(90L);
        assertEquals(Optional.of(teamEntity.get(0).getTotalScore()), Optional.of(100L));
    }

    @Test
    public void whenFindByTotalScore_thenReturnNull() {
        List<TeamEntity> teamEntityList = teamRepository.findByTotalScore(101L);
        assertTrue(teamEntityList.isEmpty());
    }

}

package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.PlayerEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryTeam;
import com.BallInTheNet.Basketball.Models.Game;
import com.BallInTheNet.Basketball.Models.Team;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
public class TeamServiceTest {

    @Mock
    private RepositoryTeam repositoryTeam;

    @Mock
    private MappingService mappingService;

    private TeamService teamService;


    private List<TeamEntity> teamEntityList = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        teamService = new TeamService(repositoryTeam);

        TeamEntity teamEntity = new TeamEntity();
        List<PlayerEntity> playerList = new ArrayList<>();
        playerList.add(new PlayerEntity("Aneta", "Wrobel", 30, 30, true, 100, teamEntity));

        teamEntity.setName("Warsaw");
        teamEntity.setPlayerEntityList(playerList);
        teamEntity.setTotalScore(100L);

        teamEntityList.add(teamEntity);
        repositoryTeam.save(teamEntity);
        when(repositoryTeam.findByName(any())).thenReturn(teamEntityList);
       // when(repositoryTeam.findByNameEquals(any())).thenReturn(teamEntity);
        when(repositoryTeam.findByTotalScore(any())).thenReturn(teamEntityList);

    }

    @Test
    public void whenFindByNameThenReturnListOfTeams() {

        List<Team> team = teamService.findByName("Sanok");
        assertNotNull(team);
        assertEquals(team.get(0).getName(), teamEntityList.get(0).getName());
    }

    @Test
    public void whenFindByNameEqualsThenReturnTeam() {

        Team team = teamService.findByNameEquals("New York");
        assertNotNull(team);
        assertEquals(team.getName(), "Warsaw");

    }

    @Test
    public void whenFindByTotalScoreThenReturnListOfTeams() {

        List<Team> team = teamService.findByTotalScore(1L);
        assertNotNull(team);
        assertEquals(java.util.Optional.ofNullable(team.get(0).getTotalScore()), Optional.of(100L));

    }

    @Test
    public void findByTotalScoreTest() {
        Long totalScoreFromTest = 100L;
        when(repositoryTeam.findByTotalScore(totalScoreFromTest)).thenReturn(teamEntityList);
        List<Team> teamList = teamService.findByTotalScore(totalScoreFromTest);
        assertEquals(1, teamList.size());
        assertEquals(Long.valueOf(100L), teamList.get(0).getTotalScore());
    }

    @Test
    public void editTeamTest() {
        Team team = new Team();
        team.setName("Test");
        team.setTotalScore(200L);
        team.setPlayerList(null);
        when(mappingService.map(teamEntityList.get(0))).thenReturn(team);
        //when(repositoryTeam.existsById(teamEntityList.get(0).getTeamId())).thenReturn(true);
        //when(repositoryTeam.findAllById(teamEntityList.get(0).getTeamId()).get()).thenReturn(team)
        Team team1 = teamService.editTeam(teamEntityList.get(0).getTeamId(), team);

        assertEquals(team.getName(), team1.getName());

    }
}

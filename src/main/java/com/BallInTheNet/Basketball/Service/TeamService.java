package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryTeam;
import com.BallInTheNet.Basketball.Models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {



    private final RepositoryTeam repositoryTeam;


    @Autowired
    public TeamService(RepositoryTeam repositoryTeam) {
        this.repositoryTeam = repositoryTeam;
    }

    private TeamEntity map(Team team) {

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(team.getName());
        teamEntity.setPlayerEntityList(null); //TO DO mapowanie losty
        teamEntity.setTotalScore(team.getTotalScore());
        return teamEntity;
    }

    private Team map(TeamEntity teamEntity) {
        Team team = new Team();
        team.setName(teamEntity.getName());
        team.setPlayerList(null); //TO DO mapowanie listy
        team.setTotalScore(teamEntity.getTotalScore());
        return team;
    }

    public List<Team> getTeams() {
        List<Team> teamList = new ArrayList<>();
        repositoryTeam.findAll().forEach(element -> teamList.add(map(element)));
        return teamList;
    }

    public List<Team> findByName(String teamName) {
        List<Team> teamList = new ArrayList<>();
        repositoryTeam.findByName(teamName).forEach(element -> teamList.add(map(element)));
        return teamList;
    }

    public Team findByNameEquals(String teamName) {
        TeamEntity teamEntity;
        teamEntity = repositoryTeam.findByNameEquals(teamName);
        return map(teamEntity);
    }

    public List<Team> findByTotalScore(Long totalScore) {
        List<Team> teamList = new ArrayList<>();
        repositoryTeam.findByTotalScore(totalScore).forEach(element -> teamList.add(map(element)));
        return teamList;
    }


    public Long addTeam(Team team) {
        return repositoryTeam.save(map(team)).getTeamId();
    }

    public boolean removeTeam(Long id) {
        boolean isDeleted = false;
        if (repositoryTeam.existsById(id)) {
            repositoryTeam.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    public Team editTeam(Long id, Team team) {

        if (repositoryTeam.existsById(id)) {
            TeamEntity newEntity = repositoryTeam.findById(id).get();
            newEntity.setName(team.getName());
            newEntity.setPlayerEntityList(null);//TO DO mapowanie listy
            newEntity.setTotalScore(team.getTotalScore());
            repositoryTeam.save(newEntity);
            return map(newEntity);
        }
        return null;
    }
}

package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryMySQL;
import com.BallInTheNet.Basketball.Models.Game;
import com.BallInTheNet.Basketball.Models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    RepositoryMySQL repositoryMySQL;

    @Autowired
    GameService gameService;


    private TeamEntity map(Team team) {

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(team.getName());
        teamEntity.setGameEntity(getListOfEntity(team));
        teamEntity.setTotalScore(team.getTotalScore());
        return teamEntity;
    }

    private List<GameEntity> getListOfEntity(Team team) {
        List<GameEntity> listOfGameEntity = new ArrayList<>();
        for (int i = 0; i < team.getGame().size(); i++) {
            listOfGameEntity.add(gameService.map(team.getGame().get(i)));
        }
        return listOfGameEntity;
    }

}

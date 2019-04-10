package com.BallInTheNet.Basketball.Domain.Repository;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryGame extends JpaRepository<GameEntity,Long> {
    List<GameEntity> findByTeamHome(String teamHome);
    List<GameEntity> findByTeamAway(String teamAway);
}

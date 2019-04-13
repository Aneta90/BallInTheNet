package com.BallInTheNet.Basketball.Domain.Repository;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryGame extends JpaRepository<GameEntity,Long> {

    List<GameEntity> findByTeamHome(String teamHome);
    List<GameEntity> findByTeamAway(String teamAway);
    @Query("Select g from GameEntity g where g.date > CURRENT_DATE")
    List<GameEntity> findByFutureGames();
    GameEntity findGameByGameId(Long game_id); //??TpRemove??
}

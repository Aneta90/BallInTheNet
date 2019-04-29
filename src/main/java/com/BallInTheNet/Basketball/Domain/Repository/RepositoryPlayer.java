package com.BallInTheNet.Basketball.Domain.Repository;

import com.BallInTheNet.Basketball.Domain.EntityModels.PlayerEntity;
import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import com.BallInTheNet.Basketball.Models.Player;
import com.BallInTheNet.Basketball.Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryPlayer extends JpaRepository<PlayerEntity, Long> {
// @Query("Select p from PlayerEntity p JOIN TeamEntity t ON t.teamId = ?1 AND p.teamEntity.getTeamId = t.teamId")
//    List<PlayerEntity> findAllPlayersByTeamId (Long id);

    List<PlayerEntity> findAllByTeamEntity(TeamEntity teamEntity);

    List<PlayerEntity> findAllBySurName(String surname);

    List<PlayerEntity> findAllByAgeAfter(int age);

    List<PlayerEntity> findAllByAgeBefore(int age);

    List<PlayerEntity> findAllByIsInjuredTrue();
}
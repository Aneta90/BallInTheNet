package com.BallInTheNet.Basketball.Domain.Repository;

import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryTeam extends JpaRepository<TeamEntity, Long> {

    List<TeamEntity> findByName(String name);
    @Query("Select t from TeamEntity t where t.totalScore >= ?1")
    List<TeamEntity> findByTotalScore(Long totalScore);

}

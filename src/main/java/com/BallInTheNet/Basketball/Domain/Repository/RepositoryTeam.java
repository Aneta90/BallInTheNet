package com.BallInTheNet.Basketball.Domain.Repository;

import com.BallInTheNet.Basketball.Domain.EntityModels.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTeam extends JpaRepository<TeamEntity,Long> {
}

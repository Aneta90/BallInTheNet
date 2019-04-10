package com.BallInTheNet.Basketball.Domain.Repository;


import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryMySQL extends JpaRepository<GameEntity, Long> {
}

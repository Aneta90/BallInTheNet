package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryMySQL;
import com.BallInTheNet.Basketball.Models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final
    RepositoryMySQL repositoryMySQL;

    @Autowired
    public GameService(RepositoryMySQL repositoryMySQL) {
        this.repositoryMySQL = repositoryMySQL;
    }




}

package com.BallInTheNet.Basketball.Models;


import lombok.AccessLevel;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;


public class Team {

    private String name;
    private List<Player> playerList;
    private List<Game> game;
    private Long totalScore;

    public Team() {
    }

    public Team(String name, List<Player> playerList, List<Game> game, Long totalScore) {
        this.name = name;
        this.playerList = playerList;
        this.game = game;
        this.totalScore = totalScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Game> getGame() {
        return game;
    }

    public void setGame(List<Game> game) {
        this.game = game;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "TeamEntity{" +
                ", name='" + name + '\'' +
                ", playerList=" + playerList +
                ", game=" + game +
                ", totalScore=" + totalScore +
                '}';
    }
}

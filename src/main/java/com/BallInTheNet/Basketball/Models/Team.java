package com.BallInTheNet.Basketball.Models;

import com.BallInTheNet.Basketball.Domain.EntityModels.GameEntity;

import java.util.List;


public class Team {

    private String name;
    private List<Player> playerList;
    private Long totalScore;

    public Team() {
    }

    public Team(String name, List<Player> playerList, Long totalScore) {
        this.name = name;
        this.playerList = playerList;
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


    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }


    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", playerList=" + playerList +
                ", totalScore=" + totalScore +
                '}';
    }
}

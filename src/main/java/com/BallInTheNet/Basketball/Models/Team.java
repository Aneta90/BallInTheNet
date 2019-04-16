package com.BallInTheNet.Basketball.Models;

import java.util.List;


public class Team {

    private String name;
    private List<Player> playerList;
    private Long totalScore;
    private Game game;

    public Team() {
    }

    public Team(String name, List<Player> playerList, Long totalScore, Game game) {
        this.name = name;
        this.playerList = playerList;
        this.totalScore = totalScore;
        this.game = game;
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", playerList=" + playerList +
                ", totalScore=" + totalScore +
                ", game=" + game +
                '}';
    }
}

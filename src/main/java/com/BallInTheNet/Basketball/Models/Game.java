package com.BallInTheNet.Basketball.Models;


import java.time.LocalDate;
import java.util.List;


public class Game {

    private String teamHome;
    private String teamAway;
    private List<Team> team;
    private Integer teamHomeScore;
    private Integer teamAwayScore;
    private Boolean isTeamHomeWin;
    private Boolean isTeamAwayWin;
    private LocalDate date;

    public Game() {
    }

    public Game(String teamHome, String teamAway, Integer teamHomeScore,
                Integer teamAwayScore, Boolean isTeamHomeWin, Boolean isTeamAwayWin, List<Team> team, LocalDate date) {
        this.teamHome = teamHome;
        this.teamAway = teamAway;
        this.teamHomeScore = teamHomeScore;
        this.teamAwayScore = teamAwayScore;
        this.isTeamHomeWin = isTeamHomeWin;
        this.isTeamAwayWin = isTeamAwayWin;
        this.team = team;
        this.date = date;
    }


    public String getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(String teamHome) {
        this.teamHome = teamHome;
    }

    public String getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(String teamAway) {
        this.teamAway = teamAway;
    }

    public Integer getTeamHomeScore() {
        return teamHomeScore;
    }

    public void setTeamHomeScore(Integer teamHomeScore) {
        this.teamHomeScore = teamHomeScore;
    }

    public Integer getTeamAwayScore() {
        return teamAwayScore;
    }

    public void setTeamAwayScore(Integer teamAwayScore) {
        this.teamAwayScore = teamAwayScore;
    }

    public Boolean getTeamHomeWin() {
        return isTeamHomeWin;
    }

    public void setTeamHomeWine(Boolean teamHomeWin) {
        isTeamHomeWin = teamHomeWin;
    }

    public Boolean getTeamAwayWine() {
        return isTeamAwayWin;
    }

    public void setTeamAwayWine(Boolean teamAwayWin) {
        isTeamAwayWin = teamAwayWin;
    }

    public List<Team> getTeam() {
        return team;
    }

    public void setTeam(List<Team> team) {
        this.team = team;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "teamHome='" + teamHome + '\'' +
                ", teamAway='" + teamAway + '\'' +
                ", teamHomeScore=" + teamHomeScore +
                ", teamAwayScore=" + teamAwayScore +
                ", isTeamHomeWin=" + isTeamHomeWin +
                ", isTeamAwayWin=" + isTeamAwayWin +
                '}';
    }
}

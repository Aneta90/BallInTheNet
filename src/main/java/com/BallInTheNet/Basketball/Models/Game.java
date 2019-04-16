package com.BallInTheNet.Basketball.Models;


import java.time.LocalDate;
import java.util.List;

public class Game {

    private Long teamHomeId;
    private Long teamAwayId;
    private String teamHome;
    private String teamAway;
    private Integer teamHomeScore;
    private Integer teamAwayScore;
    private Boolean isTeamHomeWin;
    private Boolean isTeamAwayWin;
    private LocalDate date;

    public Game() {
    }

    public Game(Long teamHomeId, Long teamAwayId, String teamHome, String teamAway, Integer teamHomeScore, Integer teamAwayScore, Boolean isTeamHomeWin, Boolean isTeamAwayWin, LocalDate date) {
        this.teamHomeId = teamHomeId;
        this.teamAwayId = teamAwayId;
        this.teamHome = teamHome;
        this.teamAway = teamAway;
        this.teamHomeScore = teamHomeScore;
        this.teamAwayScore = teamAwayScore;
        this.isTeamHomeWin = isTeamHomeWin;
        this.isTeamAwayWin = isTeamAwayWin;
        this.date = date;
    }

    public Long getTeamHomeId() {
        return teamHomeId;
    }

    public void setTeamHomeId(Long teamHomeId) {
        this.teamHomeId = teamHomeId;
    }

    public Long getTeamAwayId() {
        return teamAwayId;
    }

    public void setTeamAwayId(Long teamAwayId) {
        this.teamAwayId = teamAwayId;
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

    public void setTeamHomeWin(Boolean teamHomeWin) {
        isTeamHomeWin = teamHomeWin;
    }
    public Boolean getTeamAwayWin() {
        return isTeamAwayWin;
    }

    public void setTeamAwayWin(Boolean teamAwayWin) {
        isTeamAwayWin = teamAwayWin;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Game{" +
                "teamHome='" + teamHome + '\'' +
                ", teamAway='" + teamAway + '\'' +
                ", teamHomeScore=" + teamHomeScore +
                ", teamAwayScore=" + teamAwayScore +
                ", isTeamHomeWin=" + isTeamHomeWin +
                ", isTeamAwayWin=" + isTeamAwayWin +
                ", date=" + date +
                '}';
    }
}

/*{
        "teamHome": "War",
        "teamAway": "Rze",
        "team": null,
        "teamHomeScore": 1,
        "teamAwayScore": 2,
        "date": "2019-04-11",
        "teamHomeWin": null,
        "teamAwayWin": null
    } */

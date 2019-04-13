package com.BallInTheNet.Basketball.Domain.EntityModels;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

   @Table(name = "Games")
   @Entity
   public class GameEntity implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long gameId;

        private String teamHome; //id

        private String teamAway;

        @ManyToMany(cascade = CascadeType.ALL)
        @JsonBackReference
        @JoinTable(name = "games_teams",
                joinColumns = @JoinColumn(name = "gameId", referencedColumnName = "gameId"),
                inverseJoinColumns = @JoinColumn(name = "teamId", referencedColumnName = "teamId"))
        private List<TeamEntity> teamEntity;

        @Column(name = "teamHomeScore"/*, nullable = false*/)
        private Integer teamHomeScore;

        @Column(name = "teamAwayScore"/*, nullable = false*/)
        private Integer teamAwayScore;

        @Column(name = "isTeamHomeWin"/*, nullable = false*/)
        private Boolean isTeamHomeWin;

        @Column(name = "isTeamAwayWin"/*, nullable = false*/)
        private Boolean isTeamAwayWin;

        @Column
        private LocalDate date;

        public GameEntity() {
        }

        public GameEntity(String teamHome, String teamAway, Integer teamHomeScore,
                          Integer teamAwayScore, Boolean isTeamHomeWin, Boolean isTeamAwayWin, List<TeamEntity> teamEntity, LocalDate date) {
            this.teamHome = teamHome;
            this.teamAway = teamAway;
            this.teamHomeScore = teamHomeScore;
            this.teamAwayScore = teamAwayScore;
            this.isTeamHomeWin = isTeamHomeWin;
            this.isTeamAwayWin = isTeamAwayWin;
            this.teamEntity = teamEntity;
            this.date = date;
        }


        public Long getGameId() {
            return gameId;
        }

        public void setGameId(Long gameId) {
            this.gameId = gameId;
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

        public List<TeamEntity> getTeamEntity() {
            return teamEntity;
        }

        public void setTeamEntity(List<TeamEntity> teamEntity) {
            this.teamEntity = teamEntity;
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
                   "gameId=" + gameId +
                   ", teamHome='" + teamHome + '\'' +
                   ", teamAway='" + teamAway + '\'' +
                   ", teamEntity=" + teamEntity +
                   ", teamHomeScore=" + teamHomeScore +
                   ", teamAwayScore=" + teamAwayScore +
                   ", isTeamHomeWin=" + isTeamHomeWin +
                   ", isTeamAwayWin=" + isTeamAwayWin +
                   ", date=" + date +
                   '}';
       }
   }

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

        private Long teamHomeId; //brak polaczenia z Teamem

        private Long teamAwayId; // brak polaczeni z Teamem -> jak polaczyc dwa atrybuty z GameEntity(teamHomeId,teamAwayId) z jednym teamId z TeamEntity? Oto jest pytanie !

        private String teamHomeName;

        private String teamAwayName;


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

       public GameEntity(Long teamHomeId, Long teamAwayId, String teamHomeName, String teamAwayName, Integer teamHomeScore, Integer teamAwayScore, Boolean isTeamHomeWin, Boolean isTeamAwayWin, LocalDate date) {
           this.teamHomeId = teamHomeId;
           this.teamAwayId = teamAwayId;
           this.teamHomeName = teamHomeName;
           this.teamAwayName = teamAwayName;
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

       public Long getGameId() {
            return gameId;
        }

        public void setGameId(Long gameId) {
            this.gameId = gameId;
        }


       public String getTeamHomeName() {
           return teamHomeName;
       }

       public void setTeamHomeName(String teamHomeName) {
           this.teamHomeName = teamHomeName;
       }

       public String getTeamAwayName() {
           return teamAwayName;
       }

       public void setTeamAwayName(String teamAwayName) {
           this.teamAwayName = teamAwayName;
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

        /*public List<TeamEntity> getTeamEntity() {
            return teamEntity;
        }

        public void setTeamEntity(List<TeamEntity> teamEntity) {
            this.teamEntity = teamEntity;
        }*/

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
                   ", teamHomeName='" + teamHomeName + '\'' +
                   ", teamAwayName='" + teamAwayName + '\'' +
                  // ", teamEntity=" + teamEntity +
                   ", teamHomeScore=" + teamHomeScore +
                   ", teamAwayScore=" + teamAwayScore +
                   ", isTeamHomeWin=" + isTeamHomeWin +
                   ", isTeamAwayWin=" + isTeamAwayWin +
                   ", date=" + date +
                   '}';
       }
   }

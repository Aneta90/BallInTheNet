package com.BallInTheNet.Basketball.Domain.EntityModels;
import com.BallInTheNet.Basketball.Models.Game;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "Teams")
@Entity
public class TeamEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    private String name;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "teamEntity", cascade = CascadeType.ALL)
    private List<PlayerEntity> playerEntityList;

    //@Setter(AccessLevel.NONE)
    private Long totalScore;

    public TeamEntity() {
    }

    public TeamEntity(String name, List<PlayerEntity> playerEntityList, Long totalScore) {
        this.name = name;
        this.playerEntityList = playerEntityList;
        this.totalScore = totalScore;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerEntity> getPlayerEntityList() {
        return playerEntityList;
    }

    public void setPlayerEntityList(List<PlayerEntity> playerEntityList) {
        this.playerEntityList = playerEntityList;
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
                "teamId=" + teamId +
                ", name='" + name + '\'' +
                ", playerEntityList=" + playerEntityList +
                ", totalScore=" + totalScore +
                '}';
    }
}

/*
  {
        "teamHome": "War",
        "teamAway": "Rze",
        "team": [{
        "name":"War",
        "totalScore":10
    }, {
        "name":"War",
        "totalScore":20
    }],
        "teamHomeScore": 1,
        "teamAwayScore": 2,
        "date": "2019-04-11",
        "teamHomeWin": null,
        "teamAwayWin": null
    }
 */
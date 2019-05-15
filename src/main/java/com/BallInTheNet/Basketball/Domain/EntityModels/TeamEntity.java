package com.BallInTheNet.Basketball.Domain.EntityModels;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "teams")
@Entity
public class TeamEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column(name = "name")
    private String name;

    @Column(name = "PK_Teams")
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "teamEntity", cascade = CascadeType.ALL)
    private List<PlayerEntity> playerEntityList;

    @Column(name = "totalScore")
    private Long totalScore;

    public TeamEntity() {
    }

    public TeamEntity(String name, List<PlayerEntity> playerEntityList, Long totalScore) {
        this.name = name;
        this.playerEntityList = playerEntityList;
        this.totalScore = totalScore;
    }

    public TeamEntity(String name, Long totalScore) {
        this.name = name;
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
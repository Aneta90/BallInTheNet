package com.BallInTheNet.Basketball.Domain.EntityModels;


import javax.persistence.*;
import java.io.Serializable;

@Table(name = "Players")
@Entity
public class PlayerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playerId")
    private Long playerId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "surName")
    private String surName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "experience")
    // default value = 0; ??
    private Integer experience;

    @Column(name = "isInjured")
    private Boolean isInjured;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private TeamEntity teamEntity;

    public PlayerEntity() {
    }

    public PlayerEntity(String firstName, String surName, Integer age, Integer experience,
                        Boolean isInjured, TeamEntity teamEntity) {
        this.firstName = firstName;
        this.surName = surName;
        this.age = age;
        this.experience = experience;
        this.isInjured = isInjured;
        this.teamEntity = teamEntity;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Boolean getInjured() {
        return isInjured;
    }

    public void setInjured(Boolean injured) {
        isInjured = injured;
    }

    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "PlayerEntity{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", age=" + age +
                '}';
    }
}

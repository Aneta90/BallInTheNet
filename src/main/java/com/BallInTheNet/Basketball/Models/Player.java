package com.BallInTheNet.Basketball.Models;


public class Player {

    private String firstName;
    private String surName;
    private Integer age;
    private Integer experience;
    private Boolean isInjured;
    private Integer rating;
    private Team team;

    public Player() {
    }

    public Player(String firstName, String surName, Integer age, Integer experience, Boolean isInjured, Integer rating, Team team) {
        this.firstName = firstName;
        this.surName = surName;
        this.age = age;
        this.experience = experience;
        this.isInjured = isInjured;
        this.rating = rating;
        this.team = team;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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

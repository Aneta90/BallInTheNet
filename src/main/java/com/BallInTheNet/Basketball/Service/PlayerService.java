package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.PlayerEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryPlayer;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryTeam;
import com.BallInTheNet.Basketball.Models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PlayerService {

    private final
    MappingService mappingService;

    private final
    RepositoryPlayer repositoryPlayer;

    private final
    RepositoryTeam repositoryTeam;

    private final
    TeamService teamService;

    @Autowired
    public PlayerService(MappingService mappingService, RepositoryPlayer repositoryPlayer,
                         RepositoryTeam repositoryTeam, TeamService teamService) {
        this.mappingService = mappingService;
        this.repositoryPlayer = repositoryPlayer;
        this.repositoryTeam = repositoryTeam;
        this.teamService = teamService;
    }

    public List<Player> getListOfPlayer() {
        List<PlayerEntity> playerEntityList = repositoryPlayer.findAll();
        List<Player> playerList = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntityList) {
            playerList.add(mappingService.map(playerEntity));
        }
        return playerList;
    }

    public List<Player> findAllPlayersInTeam(String teamName) {
        List<PlayerEntity> playerEntityList =
                repositoryPlayer.findAllByTeamEntity(mappingService.map(teamService.findByNameEquals(teamName)));
        List<Player> playerList = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntityList) {
            playerList.add(mappingService.map(playerEntity));
        }
        return playerList;
    }

    public Long savePlayer(Player player) {
        return mappingService.map(player).getPlayerId();
    }

    public List<Player> playersWithGivenName(String name) {
        List<PlayerEntity> playerEntityList = repositoryPlayer.findAllBySurName(name);
        List<Player> playerList = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntityList) {
            playerList.add(mappingService.map(playerEntity));
        }
        return playerList;
    }

    public List<Player> findOlderThen(int age) {
        List<PlayerEntity> playerEntityList = repositoryPlayer.findAllByAgeAfter(age);
        List<Player> playerList = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntityList) {
            playerList.add(mappingService.map(playerEntity));
        }
        return playerList;
    }

    public List<Player> findYoungerThen(int age) {
        List<PlayerEntity> playerEntityList = repositoryPlayer.findAllByAgeBefore(age);
        List<Player> playerList = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntityList) {
            playerList.add(mappingService.map(playerEntity));
        }
        return playerList;
    }

    public Boolean removePlayer(Long id) {
        boolean isDeleted = false;
        if (repositoryPlayer.existsById(id)) {
            repositoryPlayer.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    public List<Player> listOfInjuredPlayers() {
        List<PlayerEntity> playerEntityList = repositoryPlayer.findAllByIsInjuredTrue();
        List<Player> playerList = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntityList) {
            playerList.add(mappingService.map(playerEntity));
        }
        return playerList;

    }

    // do poprawy mapowania
    public Player editPlayer(Player player, Long id) {
        if (repositoryPlayer.existsById(id)) {
            PlayerEntity playerEntity = repositoryPlayer.getOne(id);
            playerEntity.setExperience(player.getExperience());
            playerEntity.setAge(player.getAge());
            playerEntity.setInjured(player.getInjured());
            playerEntity.setRating(player.getRating());
            playerEntity.setTeamEntity(null); //do przepatrzenia...
            playerEntity.setFirstName(player.getFirstName());
            playerEntity.setSurName(player.getSurName());
            repositoryPlayer.save(playerEntity);
            return mappingService.map(playerEntity);
        }
        return null;
    }
}


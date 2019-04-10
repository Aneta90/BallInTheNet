package com.BallInTheNet.Basketball.Service;

import com.BallInTheNet.Basketball.Domain.EntityModels.PlayerEntity;
import com.BallInTheNet.Basketball.Domain.Repository.RepositoryPlayer;
import com.BallInTheNet.Basketball.Models.Player;
import com.BallInTheNet.Basketball.Models.Team;
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

    @Autowired
    public PlayerService(MappingService mappingService, RepositoryPlayer repositoryPlayer) {
        this.mappingService = mappingService;
        this.repositoryPlayer = repositoryPlayer;
    }


    public List<Player> getListOfPlayer() {
        List<PlayerEntity> playerEntityList = repositoryPlayer.findAll();
        List<Player> playerList = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntityList) {
            playerList.add(mappingService.map(playerEntity));
        }
        return playerList;
    }

    public List<Player> fineAllPlayersInTeam(Team team) {
        List<PlayerEntity> playerEntityList = repositoryPlayer.findAllByTeamEntity(mappingService.map(team));
        List<Player> playerList = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntityList) {
            playerList.add(mappingService.map(playerEntity));
        }
        return playerList;
    }

    public Boolean savePlayer(PlayerEntity playerEntity) {

        // skonczyc implementacje
        return false;
    }

    public Player updatePlayer(Player player) {
        List<PlayerEntity> listOfPlayerEntity = repositoryPlayer.findAllBySurName(player.getSurName());

        for (int i = 0; i < listOfPlayerEntity.size(); i++) {
            if (mappingService.map(listOfPlayerEntity.get(i)) == (player)) { //equals??
                PlayerEntity playerEntity = listOfPlayerEntity.get(i);
                playerEntity.setFirstName(player.getFirstName());
                playerEntity.setSurName(player.getSurName());
                playerEntity.setTeamEntity(mappingService.map(player.getTeam()));
                playerEntity.setRating(player.getRating());
                playerEntity.setInjured(player.getInjured());
                playerEntity.setAge(player.getAge());
                playerEntity.setExperience(player.getExperience());
                savePlayer(playerEntity);
                return player;
            }


        }
        return null;
    }
}

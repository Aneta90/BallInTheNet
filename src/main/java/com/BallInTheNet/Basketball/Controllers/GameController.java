package com.BallInTheNet.Basketball.Controllers;

import com.BallInTheNet.Basketball.Models.Game;
import com.BallInTheNet.Basketball.Models.Player;
import com.BallInTheNet.Basketball.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/addNewGame")
    public Long addNewPlayer(@RequestBody Game game){
        return null;
    }

    @DeleteMapping("/removeGame/{id}")
    public boolean remove(@PathVariable Long id) {
        return false;
    }




}

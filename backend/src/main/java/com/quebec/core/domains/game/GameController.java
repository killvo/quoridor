package com.quebec.core.domains.game;

import com.quebec.core.domains.game.dto.GameStartRequest;
import com.quebec.core.domains.game.dto.GameStartResponse;
import com.quebec.core.domains.game.dto.MovePlaceWallRequest;
import com.quebec.core.domains.game.dto.MovePlaceWallResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("game")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

//    @PostMapping("start")
//    public GameStartResponse getTestMessage(@RequestBody GameStartRequest request) {
//        return gameService.startGame(request);
//    }

    @PostMapping("start_two_people")
    public void start2people() {
        gameService.startTwoPeople();
    }

    @PostMapping("start_with_bot")
    public void startWithBot() {
        gameService.startWithBot();
    }

    @PostMapping("move_place_wall")
    public MovePlaceWallResponse placeWall(@RequestBody MovePlaceWallRequest request) {
        return gameService.placeWall(request);
    }
}

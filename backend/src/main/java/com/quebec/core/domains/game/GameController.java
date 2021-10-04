package com.quebec.core.domains.game;

import com.quebec.core.domains.game.dto.GameStartWithBotResponse;
import com.quebec.core.domains.game.dto.GameStartWithPlayersResponse;
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

    @PostMapping("start_two_people")
    public GameStartWithPlayersResponse start2people() {
        return gameService.startTwoPeople();
    }

    @PostMapping("start_with_bot")
    public GameStartWithBotResponse startWithBot() {
        return gameService.startWithBot();
    }

    @PostMapping("move_place_wall")
    public MovePlaceWallResponse placeWall(@RequestBody MovePlaceWallRequest request) {
        return gameService.placeWall(request);
    }

    // TODO: create controller that make player change position move

    // TODO: create controller that make bot move
}

package com.quebec.core.domains.game;

import com.quebec.core.domains.game.dto.GameStartRequest;
import com.quebec.core.domains.game.dto.GameStartResponse;
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

    @PostMapping("start")
    public GameStartResponse getTestMessage(@RequestBody GameStartRequest request) {
        return gameService.startGame(request);
    }
}

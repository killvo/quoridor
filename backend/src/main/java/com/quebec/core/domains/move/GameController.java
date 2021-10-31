package com.quebec.core.domains.move;

import com.quebec.core.domains.bot.dto.BotResponse;
import com.quebec.core.domains.move.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("move")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("place_wall")
    public PlaceWallResponse placeWall(@RequestBody PlaceWallRequest request) {
        System.out.println(request);
        return gameService.placeWall(request);
    }

    @PostMapping("make_move")
    public MakeMoveResponse makeMove(@RequestBody MakeMoveRequest request) {
        return gameService.makeMove(request);
    }

    @PostMapping("bot_move")
    public BotResponse getBotMove() {
        return gameService.getBotMove();
    }
}

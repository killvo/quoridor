package com.quebec.core.domains.initializer;

import com.quebec.core.domains.initializer.dto.GameStartWithBotResponse;
import com.quebec.core.domains.initializer.dto.GameStartWithPlayersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("init")
public class GameInitController {
    private final GameInitService gameInitService;

    @Autowired
    public GameInitController(GameInitService gameInitService) {
        this.gameInitService = gameInitService;
    }

    @PostMapping("start_two_people")
    public GameStartWithPlayersResponse start2people() {
        return gameInitService.startTwoPeople();
    }

    @PostMapping("start_with_bot")
    public GameStartWithBotResponse startWithBot() {
        return gameInitService.startWithBot();
    }

    @PostMapping("stop")
    public void resetGame() {
        gameInitService.stopGame();
    }
}

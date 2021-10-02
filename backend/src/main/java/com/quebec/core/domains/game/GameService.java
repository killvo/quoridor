package com.quebec.core.domains.game;

import com.quebec.core.domains.game.dto.GameStartRequest;
import com.quebec.core.domains.game.dto.GameStartResponse;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    public GameService() {
    }

    public GameStartResponse startGame(GameStartRequest request) {
        var response = new GameStartResponse(
                "FirstPlayerName: " + request.getFirstPlayerName()
                        + "\nSecondPlayerName: " + request.getSecondPlayerName()
        );
        return response;
    }
}

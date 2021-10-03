package com.quebec.core.domains.game;

import com.quebec.core.domains.game.dto.GameStartRequest;
import com.quebec.core.domains.game.dto.GameStartResponse;
import com.quebec.core.domains.game.dto.MovePlaceWallRequest;
import com.quebec.core.domains.game.dto.MovePlaceWallResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private Game game;
    private GameWithPlayer gameWithPlayer;
    private GameWithBot gameWithBot;

    public GameService(GameWithPlayer gameWithPlayer, GameWithBot gameWithBot) {
        this.gameWithPlayer = gameWithPlayer;
        this.gameWithBot = gameWithBot;
    }

    //    public GameStartResponse startGame(GameStartRequest request) {
//        var response = new GameStartResponse(
//                "FirstPlayerName: " + request.getFirstPlayerName()
//                        + "\nSecondPlayerName: " + request.getSecondPlayerName()
//        );
//        return response;
//    }

    public void startTwoPeople() {
        this.game = gameWithPlayer;
//        game.setPlayer1(new ViewPlayer());
//        game.setPlayer2(new ViewPlayer());
    }

    public void startWithBot() {
        this.game = gameWithBot;
//        game.setPlayer1(new ViewPlayer());
//        game.setPlayer2(new Bot());
    }

    public MovePlaceWallResponse placeWall(MovePlaceWallRequest request) {
        int result = game.placeWall(request.getX_corner(), request.getY_corner(), request.getOrientation());
        return new MovePlaceWallResponse(result);
    }
}
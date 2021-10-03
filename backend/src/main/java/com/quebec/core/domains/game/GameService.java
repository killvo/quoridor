package com.quebec.core.domains.game;

import com.quebec.core.domains.game.dto.MovePlaceWallRequest;
import com.quebec.core.domains.game.dto.MovePlaceWallResponse;
import com.quebec.core.domains.player.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private Game game;
    private PlayerService playerService;

    public GameService(Game game, PlayerService playerService) {
        this.game = game;
        this.playerService = playerService;
    }

    public boolean startTwoPeople() {
        return false;
    }

    public boolean startWithBot() {
        return false;
    }

    public MovePlaceWallResponse placeWall(MovePlaceWallRequest request) {
        int result = game.placeWall(request.getX_corner(), request.getY_corner(), request.getOrientation());
        return new MovePlaceWallResponse(result);
    }
}
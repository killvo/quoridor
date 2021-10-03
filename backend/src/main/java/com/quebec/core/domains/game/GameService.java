package com.quebec.core.domains.game;

import com.quebec.core.domains.game.dto.GameStartWithBotResponse;
import com.quebec.core.domains.game.dto.GameStartWithPlayersResponse;
import com.quebec.core.domains.game.dto.MovePlaceWallRequest;
import com.quebec.core.domains.game.dto.MovePlaceWallResponse;
import com.quebec.core.domains.player.PlayerService;
import com.quebec.core.domains.player.model.Player;
import com.quebec.core.domains.player.model.Role;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private Game game;
    private PlayerService playerService;

    public GameService(Game game, PlayerService playerService) {
        this.game = game;
        this.playerService = playerService;
    }

    public GameStartWithPlayersResponse startTwoPeople() {
        Player player1 = playerService.createNewPlayer(Role.PLAYER);
        Player player2 = playerService.createNewPlayer(Role.PLAYER);
        return new GameStartWithPlayersResponse(player1, player2);
    }

    public GameStartWithBotResponse startWithBot() {
        Player player1 = playerService.createNewPlayer(Role.PLAYER);
        playerService.createNewPlayer(Role.BOT);
        return new GameStartWithBotResponse(player1);
    }

    // TODO: add method for player change position move
    /*
        1. Get player id from ChangePositionRequest
        2. Get Player object from PlayerService by id
        3. Check the ability of player to make move (maybe create field with id of player that make last move and compare it)
        4. Call method from Game object(need to transfer player id) and make move
        5. Return move results
        */

    public MovePlaceWallResponse placeWall(MovePlaceWallRequest request) {
        /*
        1. Get player id from MovePlaceWallRequest
        2. Get Player object from PlayerService by id
        3. Check the ability of player to make move
        4. Check is player have available wall to place
        5. Call method from Game object(need to transfer player id) and place wall
        6. Return move results
        */
        int result = game.placeWall(request.getX_corner(), request.getY_corner(), request.getOrientation());
        return new MovePlaceWallResponse(result);
    }

    // TODO: add method that return bot move
    /*
        1. Check is we have Player with Role.BOT in PlayerRepository
        2. Get Bot Player object from PlayerService
        3. Check the ability of bot to make move
        4. Call method from Game object(need to transfer bot id) and make move
        5. Return move results
        */
}
package com.quebec.core.domains.move;

import com.quebec.core.domains.board.BoardService;
import com.quebec.core.domains.bot.BotPlayer;
import com.quebec.core.domains.bot.dto.BoardStateForBotDto;
import com.quebec.core.domains.bot.dto.BotResponse;
import com.quebec.core.domains.move.dto.*;
import com.quebec.core.domains.move.exceptions.BotPlayerDoesNotExistException;
import com.quebec.core.domains.move.exceptions.CannotPlaceWallException;
import com.quebec.core.domains.move.exceptions.GameEndedWithWinner;
import com.quebec.core.domains.move.exceptions.NotEnoughWallsException;
import com.quebec.core.domains.player.PlayerService;
import com.quebec.core.domains.player.model.Player;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class GameService {
    private PlayerService playerService;
    private BoardService boardService;
    private BotPlayer botPlayer;

    public GameService(PlayerService playerService, BoardService boardService, BotPlayer botPlayer) {
        this.playerService = playerService;
        this.boardService = boardService;
        this.botPlayer = botPlayer;
    }

    public MakeMoveResponse makeMove(MakeMoveRequest request) {
        Player player = playerService.getPlayer(request.getId());
        MakeMoveResponse response = boardService.makeMove(request);
        String newPosition = response.getXCorner() + "" + response.getYCorner();
        // TODO: Decide if this form of finishing is ok
        if (Arrays.asList(player.getFinishLine()).contains(newPosition)) throw new GameEndedWithWinner("Game over, Winner " + player.getId());
        return boardService.makeMove(request);
    }


    public PlaceWallResponse placeWall(PlaceWallRequest request) {
        Player player = playerService.getPlayer(request.getId());
        if (player.getAvailableWallsAmount() <= 0) {
            throw new NotEnoughWallsException("Not enough available walls to place.");
        }
        PlaceWallResponse response = boardService.placeWall(request);
        if (response.getId() == null) {
            throw new CannotPlaceWallException("Failed to place wall.");
        }
        int wallsLeft = playerService.decreaseWallsCount(player.getId());
        response.setWallsAmount(wallsLeft);
        return response;
    }

    public BotResponse getBotMove() {
        Optional<Player> botPlayerOptional = playerService.getBotPlayer();
        if (botPlayerOptional.isEmpty()) {
            throw new BotPlayerDoesNotExistException("Bot player does not exist.");
        }

        return botPlayer.getBotMove(new BoardStateForBotDto());
    }

}
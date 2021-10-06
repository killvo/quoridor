package com.quebec.core.domains.move;

import com.quebec.core.domains.board.BoardService;
import com.quebec.core.domains.bot.BotPlayer;
import com.quebec.core.domains.bot.dto.BoardStateForBotDto;
import com.quebec.core.domains.bot.dto.BotResponse;
import com.quebec.core.domains.move.dto.*;
import com.quebec.core.domains.move.exceptions.NotEnoughWallsException;
import com.quebec.core.domains.player.PlayerService;
import com.quebec.core.domains.player.model.Player;
import org.springframework.stereotype.Service;

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
        // Check the ability of player to make move (maybe create field with id of player that make last move and compare it
        return boardService.makeMove(request);
    }


    public PlaceWallResponse placeWall(PlaceWallRequest request) {
        Player player = playerService.getPlayer(request.getId());
        if (player.getAvailableWallsCount() <= 0) {
            throw new NotEnoughWallsException("Not enough available walls to place.");
        }
        // Check the ability of player to make move (maybe create field with id of player that make last move and compare it
        return boardService.placeWall(request);
    }

    public BotResponse getBotMove() {
        /*
        1. Check is we have Player with Role.BOT in PlayerRepository
        2. Get Bot Player object from PlayerService
        */

        return botPlayer.getBotMove(new BoardStateForBotDto());
    }

}
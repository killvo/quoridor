package com.quebec.core.domains.initializer;

import com.quebec.core.domains.board.BoardService;
import com.quebec.core.domains.initializer.dto.GameStartWithBotResponse;
import com.quebec.core.domains.initializer.dto.GameStartWithPlayersResponse;
import com.quebec.core.domains.player.PlayerService;
import com.quebec.core.domains.player.model.FinishLine;
import com.quebec.core.domains.player.model.Player;
import com.quebec.core.domains.player.model.Role;
import org.springframework.stereotype.Service;

@Service
public class GameInitService {
    private PlayerService playerService;
    private BoardService boardService;

    public GameInitService(PlayerService playerService, BoardService boardService) {
        this.playerService = playerService;
        this.boardService = boardService;
    }

    public GameStartWithPlayersResponse startTwoPeople() {
        Player player1 = playerService.createNewPlayer(Role.PLAYER, FinishLine.BOTTOM);
        Player player2 = playerService.createNewPlayer(Role.PLAYER, FinishLine.TOP);
        boardService.startGame(player1, player2);
        return new GameStartWithPlayersResponse(player1, player2);
    }

    public GameStartWithBotResponse startWithBot() {
        Player player1 = playerService.createNewPlayer(Role.PLAYER, FinishLine.BOTTOM);
        Player player2 = playerService.createNewPlayer(Role.BOT, FinishLine.TOP);
        boardService.startGame(player1, player2);
        return new GameStartWithBotResponse(player1, player2);
    }

    public void resetGame() {
        boardService.resetBoard();
        boardService.resetPlayers();
        playerService.removePlayers();
    }
}
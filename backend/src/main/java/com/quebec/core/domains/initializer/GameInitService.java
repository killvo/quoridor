package com.quebec.core.domains.initializer;

import com.quebec.core.domains.board.BoardService;
import com.quebec.core.domains.board.dto.StartGamePlayers;
import com.quebec.core.domains.initializer.dto.GameStartWithBotResponse;
import com.quebec.core.domains.initializer.dto.GameStartWithPlayersResponse;
import com.quebec.core.domains.player.PlayerService;
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
        Player player1 = playerService.createNewPlayer(Role.PLAYER);
        Player player2 = playerService.createNewPlayer(Role.PLAYER);
        boardService.startGame(new StartGamePlayers(player1.getId(), player2.getId()));
        return new GameStartWithPlayersResponse(player1, player2);
    }

    public GameStartWithBotResponse startWithBot() {
        Player player1 = playerService.createNewPlayer(Role.PLAYER);
        Player player2 = playerService.createNewPlayer(Role.BOT);
        boardService.startGame(new StartGamePlayers(player1.getId(), player2.getId()));
        return new GameStartWithBotResponse(player1);
    }

    public boolean resetGame() {
        return boardService.resetBoard();
        // Треба додати метод очистки гравців
    }
}
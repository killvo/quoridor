package com.quebec.core.domains.initializer;

import com.quebec.core.cli.model.PlayerColor;
import com.quebec.core.domains.board.BoardService;
import com.quebec.core.domains.initializer.dto.GameStartWithBotResponse;
import com.quebec.core.domains.initializer.dto.GameStartWithPlayersResponse;
import com.quebec.core.domains.player.PlayerService;
import com.quebec.core.domains.player.model.FinishLine;
import com.quebec.core.domains.player.model.Player;
import com.quebec.core.domains.player.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameInitService {
    private final PlayerService playerService;
    private final BoardService boardService;

    public GameInitService(PlayerService playerService, BoardService boardService) {
        this.playerService = playerService;
        this.boardService = boardService;
    }

    public GameStartWithPlayersResponse startTwoPeople() {
        Player player1 = playerService.createNewPlayer(Role.PLAYER, FinishLine.TOP);
        Player player2 = playerService.createNewPlayer(Role.PLAYER, FinishLine.BOTTOM);
        boardService.startGame(player1, player2);
        return new GameStartWithPlayersResponse(player1, player2);
    }

    public GameStartWithBotResponse startWithBot() {
        Player player1 = playerService.createNewPlayer(Role.PLAYER, FinishLine.TOP);
        Player player2 = playerService.createNewPlayer(Role.BOT, FinishLine.BOTTOM);
        boardService.startGame(player1, player2);
        return new GameStartWithBotResponse(player1, player2);
    }

    /**
     * player1 - нижній гравець (4, 8)
     * player2 - верхній гравець (4, 0)
     */
    public GameStartWithBotResponse startGameAndSetBotPlayerColor(PlayerColor botColor) {
        Player player1;
        Player player2;
        if (botColor == PlayerColor.BLACK) {
            player2 = playerService.createNewPlayer(Role.BOT, FinishLine.BOTTOM);
            player1 = playerService.createNewPlayer(Role.PLAYER, FinishLine.TOP);
        } else {
            player2 = playerService.createNewPlayer(Role.BOT, FinishLine.TOP);
            player1 = playerService.createNewPlayer(Role.PLAYER, FinishLine.BOTTOM);
        }

        boardService.startGame(player1, player2);
        return new GameStartWithBotResponse(player1, player2);
    }

    public void stopGame() {
        boardService.resetBoard();
        playerService.removePlayers();
    }

    public GameStartWithPlayersResponse restartGame() {
        boardService.resetBoard();
        List<Player> players = playerService.renewPlayers();
        boardService.startGame(players.get(0), players.get(1));
        return new GameStartWithPlayersResponse(players.get(0), players.get(1));
    }
}
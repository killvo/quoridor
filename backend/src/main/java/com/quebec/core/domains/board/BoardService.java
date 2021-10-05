package com.quebec.core.domains.board;

import com.quebec.core.domains.board.dto.StartGamePlayers;
import com.quebec.core.domains.game.dto.MakeMoveRequest;
import com.quebec.core.domains.game.dto.MakeMoveResponse;
import com.quebec.core.domains.game.dto.PlaceWallRequest;
import com.quebec.core.domains.game.dto.PlaceWallResponse;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    // TODO: implement move validator

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public boolean startGame(StartGamePlayers players) {
        boardRepository.initBoard();
        boardRepository.initPlayers(players.getFirstPlayerId(), players.getSecondPlayerId());
        return true;
    }

    public PlaceWallResponse placeWall(PlaceWallRequest request){
        // TODO: implement place wall logic
        return new PlaceWallResponse();
    }

    public MakeMoveResponse makeMove(MakeMoveRequest request){
        // TODO: implement make move logic
        return new MakeMoveResponse();
    }

    public boolean resetBoard() {
        return boardRepository.resetBoard();
    }
}

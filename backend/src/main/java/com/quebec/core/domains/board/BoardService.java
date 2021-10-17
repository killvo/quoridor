package com.quebec.core.domains.board;

import com.quebec.core.domains.board.dto.StartGamePlayers;
import com.quebec.core.domains.move.dto.MakeMoveRequest;
import com.quebec.core.domains.move.dto.MakeMoveResponse;
import com.quebec.core.domains.move.dto.PlaceWallRequest;
import com.quebec.core.domains.move.dto.PlaceWallResponse;
import com.quebec.core.domains.move.exceptions.NotPossibleMoveException;
import com.quebec.core.domains.move.exceptions.NotPossiblePlaceWallException;
import com.quebec.core.domains.player.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    private MoveValidator validator;

    public BoardService(BoardRepository boardRepository, MoveValidator moveValidator) {
        this.boardRepository = boardRepository;
        this.validator = moveValidator;
    }

    public boolean startGame(StartGamePlayers players) {
        boardRepository.initBoard();
        boardRepository.initPlayers(players.getFirstPlayerId(), players.getSecondPlayerId());
        return true;
    }

    public PlaceWallResponse placeWall(PlaceWallRequest request){
        if (validator.isMoveWallPlaceValid(boardRepository.getBoard(), request)){
            boardRepository.placeWall(request.getXCorner(), request.getYCorner(), request.getOrientation());
            //TODO: Relocate response creation to GameService
            return new PlaceWallResponse(request.getId(), 0);
        } else throw new NotPossiblePlaceWallException("It is not possible to place a wall");
    }

    public MakeMoveResponse makeMove(MakeMoveRequest request){
        if (validator.isMovePlayerValid(boardRepository.getBoard(), request, boardRepository.getPlayersPositions())){
            boardRepository.makeMove(request.getId(), request.getXCorner(), request.getYCorner());
            return new MakeMoveResponse(request.getId(), request.getXCorner(), request.getYCorner());
        } else throw new NotPossibleMoveException("This move is not possible");
    }

    public void resetBoard() {
        boardRepository.resetBoard();
    }
}

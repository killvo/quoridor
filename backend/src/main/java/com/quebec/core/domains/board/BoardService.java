package com.quebec.core.domains.board;

import com.quebec.core.domains.move.dto.MakeMoveRequest;
import com.quebec.core.domains.move.dto.MakeMoveResponse;
import com.quebec.core.domains.move.dto.PlaceWallRequest;
import com.quebec.core.domains.move.dto.PlaceWallResponse;
import com.quebec.core.domains.move.exceptions.NotPossibleMoveException;
import com.quebec.core.domains.move.exceptions.NotPossiblePlaceWallException;
import com.quebec.core.domains.player.model.Player;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private BoardRepository boardRepository;
    private MoveValidator validator;

    public BoardService(BoardRepository boardRepository, MoveValidator moveValidator) {
        this.boardRepository = boardRepository;
        this.validator = moveValidator;
    }

    public void startGame(Player player1, Player player2) {
        boardRepository.initBoard();
        boardRepository.initPlayers(player1, player2);
    }

    public PlaceWallResponse placeWall(PlaceWallRequest request){
        if (validator.isMoveWallPlaceValid(boardRepository.getBoard(), boardRepository.getWalls(), request, boardRepository.getPlayersPositions())){
            boardRepository.placeWall(request.getX(), request.getY(), request.getOrientation());
            //TODO: Relocate response creation to GameService
            return new PlaceWallResponse(request.getId(), request.getX(), request.getY(), request.getOrientation(), 0);
        } else throw new NotPossiblePlaceWallException("It is not possible to place a wall");
    }

    public MakeMoveResponse makeMove(MakeMoveRequest request){
        if (validator.isMovePlayerValid(boardRepository.getBoard(), request, boardRepository.getPlayersPositions())) {
            boardRepository.makeMove(request.getId(), request.getX(), request.getY());
            return new MakeMoveResponse(request.getId(), request.getX(), request.getY(), null);
        } else throw new NotPossibleMoveException("This move is not possible");
    }

    public void resetBoard() {
        boardRepository.resetBoard();
    }
}

package com.quebec.core.domains.board;

import com.quebec.core.domains.move.MovePlayerMove;
import com.quebec.core.domains.move.PlaceWallMove;
import com.quebec.core.domains.move.dto.MakeMoveRequest;
import com.quebec.core.domains.move.dto.MakeMoveResponse;
import com.quebec.core.domains.move.dto.PlaceWallRequest;
import com.quebec.core.domains.move.dto.PlaceWallResponse;
import com.quebec.core.domains.move.exceptions.NotPossibleMoveException;
import com.quebec.core.domains.move.exceptions.NotPossiblePlaceWallException;
import com.quebec.core.domains.move.model.Orientation;
import com.quebec.core.domains.player.model.Player;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
            return new PlaceWallResponse(request.getId(), request.getX(), request.getY(), request.getOrientation(), 0);
        } else throw new NotPossiblePlaceWallException("It is not possible to place a wall");
    }

    public MakeMoveResponse makeMove(MakeMoveRequest request){
        if (validator.isMovePlayerValid(boardRepository.getBoard(), request, boardRepository.getPlayersPositions())) {
            boardRepository.makeMove(request.getId(), request.getX(), request.getY());
            return new MakeMoveResponse(request.getId(), request.getX(), request.getY(), null);
        } else throw new NotPossibleMoveException("This move is not possible");
    }

    public List<PlaceWallMove> getAllPossiblePlaceWalls(Graph<String, DefaultEdge> board, Orientation[][] walls, PlaceWallRequest request, Map<UUID, String> playerPositions) {
        List<PlaceWallMove> result = new ArrayList<>();
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                result.add(new PlaceWallMove(i, j, Orientation.HORIZONTAL));
                result.add(new PlaceWallMove(i, j, Orientation.VERTICAL));
            }
        }
        return result.stream().filter(p -> validator.isMoveWallPlaceValid(board, walls, request, playerPositions)).collect(Collectors.toList());
    }

    public List<MovePlayerMove> getAllPossibleMakeMoves(Graph<String, DefaultEdge> board, MakeMoveRequest request, Map<UUID, String> playerPositions) {
        int x = request.getX();
        int y = request.getY();

        List<MovePlayerMove> result = new ArrayList<>();
        result.add(new MovePlayerMove(x-1, y));
        result.add(new MovePlayerMove(x+1, y));
        result.add(new MovePlayerMove(x, y-1));
        result.add(new MovePlayerMove(x, y+1));

        return result.stream().filter(p -> validator.isMovePlayerValid(board, request, playerPositions)).collect(Collectors.toList());
    }

    public SimpleGraph placeWallImmutable(int x, int y, Orientation orientation){
        return boardRepository.placeWallImmutable(x, y, orientation);
    }

    public void resetBoard() {
        boardRepository.resetBoard();
    }
}

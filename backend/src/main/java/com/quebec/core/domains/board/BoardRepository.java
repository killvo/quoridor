package com.quebec.core.domains.board;

import com.quebec.core.domains.board.dto.StartGamePlayers;
import com.quebec.core.domains.board.exceptions.InvalidBoardException;
import com.quebec.core.domains.board.exceptions.PlayerNotFoundOnBoardException;
import com.quebec.core.domains.move.model.Orientation;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class BoardRepository {
    private Graph<String, DefaultEdge> board;
    private Orientation[][] walls;
    private Map<UUID, String> playersPositions;
    private final int VERTICES_AMOUNT = 81;

    public BoardRepository() {
    }

    public Graph<String, DefaultEdge> getBoard() {
        return board;
    }

    public void initBoard() {
        board = new SimpleGraph<>(DefaultEdge.class);
        walls = new Orientation[8][8];
        addAllVertices();
        addAllEdges();
    }

    private void addAllVertices() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                board.addVertex(getName(x, y));
            }
        }
    }

    private void addAllEdges() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board.addEdge(getName(x, y), getName(x, y + 1));
                board.addEdge(getName(x, y), getName(x + 1, y));
                if (x == 7) {
                    board.addEdge(getName(8, y), getName(8, y + 1));
                }
                if (y == 7) {
                    board.addEdge(getName(x, 8), getName(x + 1, 8));
                }
            }
        }
    }

    private String getName(int x, int y) {
        return x + "" + y;
    }

    private int getX(String name) {
        return name.charAt(0);
    }

    private int getY(String name) {
        return name.charAt(1);
    }

    public Map<UUID, String> getPlayersPositions() {
        return playersPositions;
    }

    public Orientation[][] getWalls() {
        return walls;
    }

    public void initPlayers(StartGamePlayers players) {
        playersPositions = new HashMap<>();
        playersPositions.put(players.getFirstPlayerId(), "04");
        playersPositions.put(players.getSecondPlayerId(), "84");
    }

    public String makeMove(UUID playerId, int x, int y) {
        checkBoardOnValid();
        if (!playersPositions.containsKey(playerId)) {
            throw new PlayerNotFoundOnBoardException("Player with given id not found on game board.");
        }
        String nextPosition = getName(x, y);
        playersPositions.put(playerId, nextPosition);
        return nextPosition;
    }

    private void checkBoardOnValid() {
        if (isBoardIncorrect()) {
            throw new InvalidBoardException("The board is not created or created incorrect.");
        }
    }

    private boolean isBoardIncorrect() {
        return board == null || board.vertexSet().size() != VERTICES_AMOUNT || board.edgeSet().size() == 0;
    }

    public String placeWall(int x, int y, Orientation orientation) {
        checkBoardOnValid();
        walls[x][y] = orientation;
        if (orientation == Orientation.VERTICAL) {
            board.removeEdge(getName(x, y), getName(x + 1, y));
            board.removeEdge(getName(x, y + 1), getName(x + 1, y + 1));
        } else {
            board.removeEdge(getName(x, y), getName(x, y + 1));
            board.removeEdge(getName(x + 1, y), getName(x + 1, y + 1));
        }
        return getName(x, y);
    }

    public void removeWall(int x, int y) {
        checkBoardOnValid();
        if(walls[x][y] != null){
            Orientation orientation = walls[x][y];
            if (orientation == Orientation.VERTICAL) {
                board.addEdge(getName(x, y), getName(x + 1, y));
                board.addEdge(getName(x, y + 1), getName(x + 1, y + 1));
            } else {
                board.addEdge(getName(x, y), getName(x, y + 1));
                board.addEdge(getName(x + 1, y), getName(x + 1, y + 1));
            }
            walls[x][y] = null;
        }
    }


    public void resetBoard() {
        board = new SimpleGraph<>(DefaultEdge.class);
    }

    public void resetPlayers() {
        playersPositions = new HashMap<>();
    }
}

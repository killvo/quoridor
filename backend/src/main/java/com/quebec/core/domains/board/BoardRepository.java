package com.quebec.core.domains.board;

import com.quebec.core.domains.board.exceptions.InvalidXYException;
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
    private Map<UUID, String> playersPositions;

    public BoardRepository() {
    }

    public Graph<String, DefaultEdge> getBoard() {
        return board;
    }

    public void initBoard() {
        board = new SimpleGraph<>(DefaultEdge.class);
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
                board.addEdge(getName(x, y), getName(x, y + 1)); // up -> down
                board.addEdge(getName(x, y), getName(x + 1, y)); // left -> right
                if (x == 7) {
                    board.addEdge(getName(8, y), getName(8, y + 1)); // up -> down
                }
                if (y == 7) {
                    board.addEdge(getName(x, 8), getName(x + 1, 8)); // left -> right
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

    public boolean initPlayers(UUID firstPlayerId, UUID secondPlayerId) {
        playersPositions = new HashMap<>();
        playersPositions.put(firstPlayerId, "04");
        playersPositions.put(secondPlayerId, "84");
        return true;
    }

//    public int getByXY(int xCorner, int yCorner) {
//        try {
//            return board[xCorner][yCorner];
//        } catch (IndexOutOfBoundsException e) {
//            throw new InvalidXYException("X or Y corner value out of bounds.");
//        }
//    }
//
//    public void setByXY(int xCorner, int yCorner, int value) {
//        try {
//            board[xCorner][yCorner] = value;
//        } catch (IndexOutOfBoundsException e) {
//            throw new InvalidXYException("X or Y corner value out of bounds.");
//        }
//    }

    public void resetBoard() {
        board = new SimpleGraph<>(DefaultEdge.class);
    }

    public void resetPlayers() {
        playersPositions = new HashMap<>();
    }
}

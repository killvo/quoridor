package com.quebec.core.domains.board;

import com.quebec.core.domains.board.exceptions.InvalidXYException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class BoardRepository {
    private int [][] tiles;
    private Map<UUID, String> playersPositions;

    public BoardRepository() {
    }

    public int[][] getBoard() {
        return tiles;
    }

    public boolean initBoard() {
        tiles = new int[9][9];
        return true;
    }

    public boolean initPlayers(UUID firstPlayerId, UUID secondPlayerId) {
        playersPositions = new HashMap<>();
        playersPositions.put(firstPlayerId, "04");
        playersPositions.put(secondPlayerId, "84");
        return true;
    }

    public int getByXY(int xCorner, int yCorner) {
        try {
            return tiles[xCorner][yCorner];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidXYException("X or Y corner value out of bounds.");
        }
    }

    public void setBoard(int[][] board) {
        this.tiles = board;
    }

    public void setByXY(int xCorner, int yCorner, int value) {
        try {
            tiles[xCorner][yCorner] = value;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidXYException("X or Y corner value out of bounds.");
        }
    }

    public boolean resetBoard() {
        tiles = new int[9][9];
        return false;
    }

    public boolean resetPlayers() {
        playersPositions = new HashMap<>();
        return false;
    }
}

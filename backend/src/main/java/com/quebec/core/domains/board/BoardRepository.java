package com.quebec.core.domains.board;

import com.quebec.core.domains.board.exceptions.InvalidXYException;
import org.springframework.stereotype.Component;

@Component
public class BoardRepository {

    private int [][] tiles;

    public BoardRepository() {
        this.tiles = new int[9][9];
    }

    public int[][] getBoard() {
        return tiles;
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
}

package com.quebec.core.domains.game;

import com.quebec.core.domains.game.model.Orientation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Game {
    private Board board;

    public Game(Board board) {
        this.board = board;
    }

    public int placeWall(int x_corner, int y_corner, Orientation orientation) {
        board.placeWall(x_corner, y_corner, orientation);
        return 0;
    }
}

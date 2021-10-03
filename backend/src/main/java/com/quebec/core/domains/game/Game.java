package com.quebec.core.domains.game;

import com.quebec.core.domains.game.dto.MovePlaceWallRequest;

public abstract class Game {
    Player player1;
    Player player2;
    Board board;

    public Game(Board board) {
        this.board = board;
    }

    //    public Game(Player player1, Player player2) {
//        this.player1 = player1;
//        this.player2 = player2;
//    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int placeWall(int x_corner, int y_corner, Orientation orientation) {
        board.placeWall(x_corner, y_corner, orientation);
        return 0;
    }
}

package com.quebec.core.domains.move.exceptions;

public class GameEndedWithWinner extends RuntimeException{
    public GameEndedWithWinner(String message) {
        super(message);
    }
}

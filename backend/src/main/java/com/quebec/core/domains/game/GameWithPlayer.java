package com.quebec.core.domains.game;

import org.springframework.stereotype.Component;

@Component
public class GameWithPlayer extends Game{

    public GameWithPlayer(Board board) {
        super(board);
    }
}

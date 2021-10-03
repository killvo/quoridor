package com.quebec.core.domains.game;

import org.springframework.stereotype.Component;

@Component
public class GameWithBot extends Game{

    public GameWithBot(Board board) {
        super(board);
    }

    public void botMakeMove(){

    }
}

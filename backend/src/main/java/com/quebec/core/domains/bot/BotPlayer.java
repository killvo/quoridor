package com.quebec.core.domains.bot;

import com.quebec.core.domains.bot.dto.BoardStateForBotDto;
import com.quebec.core.domains.bot.dto.BotResponse;
import com.quebec.core.domains.bot.exceptions.InvalidBoardStateException;
import org.springframework.stereotype.Component;

@Component
public class BotPlayer {

    public BotResponse getBotMove(BoardStateForBotDto boardState) {
        if (boardState == null) {
            throw new InvalidBoardStateException("Board state incorrect!");
        }
        // Виконати обрахунки та повернути хід від бота
        return new BotResponse();
    }
}

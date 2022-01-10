package com.quebec.core.domains.bot.dto;

import com.quebec.core.domains.move.MovePlayerMove;
import com.quebec.core.domains.move.PlaceWallMove;
import com.quebec.core.domains.move.model.Orientation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BotResponse {
    private int xCorner;
    private int yCorner;
    private Orientation orientation;

    public static BotResponse from(MovePlayerMove move) {
        return new BotResponse(move.getX(), move.getY(), Orientation.NONE);
    }

    public static BotResponse from(PlaceWallMove move) {
        return new BotResponse(move.getX_corner(), move.getX_corner(), move.getOrientation());
    }
}

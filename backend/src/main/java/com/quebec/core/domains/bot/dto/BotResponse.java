package com.quebec.core.domains.bot.dto;

import com.quebec.core.domains.game.model.Orientation;
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
}

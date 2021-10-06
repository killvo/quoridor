package com.quebec.core.domains.initializer.dto;

import com.quebec.core.domains.player.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameStartWithBotResponse {
    private Player firstPlayer;
}

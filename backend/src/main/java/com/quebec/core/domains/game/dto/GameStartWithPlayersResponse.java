package com.quebec.core.domains.game.dto;

import com.quebec.core.domains.player.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameStartWithPlayersResponse {
    private Player firstPlayer;
    private Player secondPlayer;
}

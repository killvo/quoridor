package com.quebec.core.domains.initializer.dto;

import com.quebec.core.domains.player.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameStartWithPlayersResponse {
    private Player firstPlayer;
    private Player secondPlayer;
}

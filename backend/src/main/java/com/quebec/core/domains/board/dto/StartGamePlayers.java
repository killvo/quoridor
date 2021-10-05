package com.quebec.core.domains.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartGamePlayers {
    private UUID firstPlayerId;
    private UUID secondPlayerId;
}

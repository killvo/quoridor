package com.quebec.core.domains.game.dto;

import lombok.Data;

@Data
public class GameStartRequest {
    private String firstPlayerName;
    private String secondPlayerName;
}

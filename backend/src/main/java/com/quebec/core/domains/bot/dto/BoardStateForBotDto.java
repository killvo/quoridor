package com.quebec.core.domains.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardStateForBotDto {
    private int[][] tiles;
    private String firstPlayerPosition;
    private String secondPlayerPosition;
}

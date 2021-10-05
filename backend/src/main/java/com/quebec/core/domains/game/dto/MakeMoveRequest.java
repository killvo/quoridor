package com.quebec.core.domains.game.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MakeMoveRequest {
    private UUID id;
    private int xCorner;
    private int yCorner;
}

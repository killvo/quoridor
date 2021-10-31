package com.quebec.core.domains.move.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MakeMoveRequest {
    private UUID id;
    private int x;
    private int y;
}

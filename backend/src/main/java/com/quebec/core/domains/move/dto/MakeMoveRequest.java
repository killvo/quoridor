package com.quebec.core.domains.move.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class MakeMoveRequest {
    private UUID id;
    private int x;
    private int y;
}

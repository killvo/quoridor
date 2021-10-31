package com.quebec.core.domains.move.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakeMoveResponse {
    private UUID id;
    private int x;
    private int y;
    private String winner;
}

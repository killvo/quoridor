package com.quebec.core.domains.player.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Player {
    private UUID id;
    private Integer availableWallsAmount;
    private Role role;
    private FinishLine finishLine;
}

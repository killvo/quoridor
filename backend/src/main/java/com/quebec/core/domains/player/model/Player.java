package com.quebec.core.domains.player.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private UUID id;
    private Integer availableWallsAmount;
    private Role role;
    private String[] finishLine;
}

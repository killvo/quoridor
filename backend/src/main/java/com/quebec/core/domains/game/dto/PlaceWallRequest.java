package com.quebec.core.domains.game.dto;

import com.quebec.core.domains.game.model.Orientation;
import lombok.Data;

import java.util.UUID;

@Data
public class PlaceWallRequest {
    private UUID id;
    private int xCorner;
    private int yCorner;
    private Orientation orientation;
}

package com.quebec.core.domains.move.dto;

import com.quebec.core.domains.move.model.Orientation;
import lombok.Data;

import java.util.UUID;

@Data
public class PlaceWallRequest {
    private UUID id;
    private int x;
    private int y;
    private Orientation orientation;
}

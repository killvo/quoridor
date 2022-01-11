package com.quebec.core.domains.move.dto;

import com.quebec.core.domains.move.model.Orientation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PlaceWallRequest {
    private UUID id;
    private int x;
    private int y;
    private Orientation orientation;
}

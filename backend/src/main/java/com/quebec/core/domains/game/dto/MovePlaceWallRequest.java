package com.quebec.core.domains.game.dto;

import com.quebec.core.domains.game.Orientation;
import lombok.Data;

@Data
public class MovePlaceWallRequest {
    private int x_corner;
    private int y_corner;
    private Orientation orientation;
}

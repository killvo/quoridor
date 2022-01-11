package com.quebec.core.domains.move;

import com.quebec.core.domains.move.dto.PlaceWallRequest;
import com.quebec.core.domains.move.dto.PlaceWallResponse;
import com.quebec.core.domains.move.model.Orientation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlaceWallMove extends Move {
    private int x_corner;
    private int y_corner;
    private Orientation orientation;

    public PlaceWallRequest toRequest(UUID userId) {
        return new PlaceWallRequest(userId, x_corner, y_corner, orientation);
    }

    public PlaceWallResponse toResponse(UUID userId, int wallsAmount) {
        return new PlaceWallResponse(userId, x_corner, y_corner, orientation, wallsAmount);
    }
}

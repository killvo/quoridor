package com.quebec.core.domains.move.dto;

import com.quebec.core.domains.move.model.Orientation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceWallResponse {
    private UUID id;
    private int x;
    private int y;
    private Orientation orientation;
    private int wallsAmount;
}

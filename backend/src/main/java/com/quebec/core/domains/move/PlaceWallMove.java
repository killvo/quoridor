package com.quebec.core.domains.move;

import com.quebec.core.domains.move.dto.MakeMoveRequest;
import com.quebec.core.domains.move.dto.MakeMoveResponse;
import com.quebec.core.domains.move.dto.PlaceWallRequest;
import com.quebec.core.domains.move.dto.PlaceWallResponse;
import com.quebec.core.domains.move.model.Orientation;

public class PlaceWallMove extends Move {
    private int x_corner;
    private int y_corner;
    private Orientation orientation;

    public PlaceWallMove() {
    }

    public PlaceWallMove(int x_corner, int y_corner, Orientation orientation) {
        this.x_corner = x_corner;
        this.y_corner = y_corner;
        this.orientation = orientation;
    }

    public PlaceWallRequest toRequest() {
        return null;
    }

    public PlaceWallResponse toResponse() {
        return null;
    }

    public int getX_corner() {
        return x_corner;
    }

    public void setX_corner(int x_corner) {
        this.x_corner = x_corner;
    }

    public int getY_corner() {
        return y_corner;
    }

    public void setY_corner(int y_corner) {
        this.y_corner = y_corner;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}

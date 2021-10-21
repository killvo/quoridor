package com.quebec.core.domains.board;

import com.quebec.core.domains.move.dto.MakeMoveRequest;
import com.quebec.core.domains.move.dto.PlaceWallRequest;
import com.quebec.core.domains.move.model.Orientation;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MoveValidator {

    public boolean isMoveWallPlaceValid(Graph<String, DefaultEdge> board, Orientation[][] walls, PlaceWallRequest request) {
        int x = request.getXCorner();
        int y = request.getYCorner();
        if (request.getOrientation() == Orientation.VERTICAL) {
            if (x >= 1 && walls[x-1][y] == Orientation.VERTICAL) return false;
            if (x <= 7 && walls[x+1][y] == Orientation.VERTICAL) return false;
        } else {
            if (y >= 1 && walls[x][y-1] == Orientation.HORIZONTAL) return false;
            if (y <= 7 && walls[x][y+1] == Orientation.HORIZONTAL) return false;
        }
        return true;
    }

    public boolean isMovePlayerValid(Graph<String, DefaultEdge> board, MakeMoveRequest request, Map<UUID, String> playerPositions) {
        String activePlayerPosition = playerPositions.get(request.getId());
        String targetPosition = request.getXCorner() + "" + request.getYCorner();

        if(playerPositions.containsValue(targetPosition)) return false;
        if(board.containsEdge(activePlayerPosition, targetPosition)) return true;

        List<String> activeNeighbours = Graphs.neighborListOf(board, activePlayerPosition);
        List<String> targetNeighbours = Graphs.neighborListOf(board, targetPosition);
        activeNeighbours.retainAll(targetNeighbours);
        for (String neighbour: activeNeighbours) {
            if (playerPositions.containsValue(neighbour)) return true;
        }
        return false;
    }
}

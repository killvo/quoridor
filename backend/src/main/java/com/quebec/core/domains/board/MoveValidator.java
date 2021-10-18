package com.quebec.core.domains.board;

import com.quebec.core.domains.move.dto.MakeMoveRequest;
import com.quebec.core.domains.move.dto.PlaceWallRequest;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MoveValidator {

    public boolean isMoveWallPlaceValid(Graph<String, DefaultEdge> board, PlaceWallRequest request) {
        //TODO: Add validation
        return false;
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

package com.quebec.core.domains.board;

import com.quebec.core.domains.move.dto.MakeMoveRequest;
import com.quebec.core.domains.move.dto.PlaceWallRequest;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class MoveValidator {

    public boolean isMoveWallPlaceValid(Graph<String, DefaultEdge> board, PlaceWallRequest request) {
        //TODO: Add validation
        return false;
    }

    public boolean isMovePlayerValid(Graph<String, DefaultEdge> board, MakeMoveRequest request, Map<UUID, String> playerPositions) {
        //TODO: Add validation
        return false;
    }
}

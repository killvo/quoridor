package com.quebec.core.domains.board;

import com.quebec.core.domains.move.dto.MakeMoveRequest;
import com.quebec.core.domains.move.dto.PlaceWallRequest;
import com.quebec.core.domains.move.model.Orientation;
import com.quebec.core.domains.player.PlayerService;
import com.quebec.core.domains.player.model.Player;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.AStarShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MoveValidator {
    PlayerService playerService;
    BoardRepository boardRepository;

    public boolean isMoveWallPlaceValid(Graph<String, DefaultEdge> board, Orientation[][] walls, PlaceWallRequest request, Map<UUID, String> playerPositions) {
        int x = request.getXCorner();
        int y = request.getYCorner();
        if (request.getOrientation() == Orientation.VERTICAL) {
            if (x >= 1 && walls[x-1][y] == Orientation.VERTICAL) return false;
            if (x <= 7 && walls[x+1][y] == Orientation.VERTICAL) return false;
        } else {
            if (y >= 1 && walls[x][y-1] == Orientation.HORIZONTAL) return false;
            if (y <= 7 && walls[x][y+1] == Orientation.HORIZONTAL) return false;
        }
        Collection<Player> players = playerService.getAll();
        Player[] p = players.toArray(new Player[2]);
        boolean flag = false;
        ConnectivityInspector<String, DefaultEdge> inspector = new ConnectivityInspector<>(board);

        boardRepository.placeWall(request.getXCorner(), request.getYCorner(), request.getOrientation());
        if (canPlayerMoveToFinish(p[0], playerPositions.get(p[0].getId()), inspector) &&
                canPlayerMoveToFinish(p[1], playerPositions.get(p[1].getId()), inspector)) flag = true;

        boardRepository.removeWall(request.getXCorner(), request.getYCorner());
        return flag;
    }

    private boolean canPlayerMoveToFinish (Player player, String position, ConnectivityInspector<String, DefaultEdge> inspector) {
        for (String finishPoint : player.getFinishLine()) {
            if(inspector.pathExists(position, finishPoint)) return true;
        }
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

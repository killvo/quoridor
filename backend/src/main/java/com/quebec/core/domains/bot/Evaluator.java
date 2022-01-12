package com.quebec.core.domains.bot;

import com.quebec.core.domains.player.model.Player;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Evaluator {

    public double evaluate(Node node, Player player) {
        Graph<String, DefaultEdge> board = node.getBoard();
        AllDirectedPaths<String, DefaultEdge> allDirectedPaths = new AllDirectedPaths<>(board);

        String playerPosition = node.getPlayerPositions().get(player.getId());

        double minimalPathSize = Integer.MAX_VALUE;
        double sumPossiblePaths = 0;
        for (String finishPoint : player.getFinishLine()) {
            List<GraphPath<String, DefaultEdge>> paths = allDirectedPaths.getAllPaths(
                    playerPosition,
                    finishPoint,
                    true,
                    null
            );
            sumPossiblePaths += paths.size();

            for (GraphPath<String, DefaultEdge> graphPath : paths){
                int length = graphPath.getLength();
                if (length < minimalPathSize) minimalPathSize = length;
            }
        }

        return sumPossiblePaths / minimalPathSize;
    }
}

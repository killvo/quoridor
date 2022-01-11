package com.quebec.core.domains.bot;

import com.quebec.core.domains.move.Move;
import com.quebec.core.domains.move.model.Orientation;
import lombok.Data;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class Node {
    Move moveThatLeadToIt;
    Graph<String, DefaultEdge> board;
    Map<UUID, String> playerPositions;
    Orientation[][] walls;
    boolean isMaxPlayer;
    double score;
    List<Node> children;

    public void addChild(Node child) {
        children.add(child);
    }
}

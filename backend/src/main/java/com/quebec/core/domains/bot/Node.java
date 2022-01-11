package com.quebec.core.domains.bot;

import com.quebec.core.domains.move.Move;
import com.quebec.core.domains.move.model.Orientation;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Node {
    Move moveThatLeadToIt;
    Graph<String, DefaultEdge> board;
    Map<UUID, String> playerPositions;
    Orientation[][] walls;
    boolean isMaxPlayer;
    int score;
    List<Node> children;

    public Move getMoveThatLeadToIt() {
        return moveThatLeadToIt;
    }

    public void setMoveThatLeadToIt(Move moveThatLeadToIt) {
        this.moveThatLeadToIt = moveThatLeadToIt;
    }

    public Graph<String, DefaultEdge> getBoard() {
        return board;
    }

    public void setBoard(Graph<String, DefaultEdge> board) {
        this.board = board;
    }

    public Map<UUID, String> getPlayerPositions() {
        return playerPositions;
    }

    public void setPlayerPositions(Map<UUID, String> playerPositions) {
        this.playerPositions = playerPositions;
    }

    public Orientation[][] getWalls() {
        return walls;
    }

    public void setWalls(Orientation[][] walls) {
        this.walls = walls;
    }

    public boolean isMaxPlayer() {
        return isMaxPlayer;
    }

    public void setMaxPlayer(boolean maxPlayer) {
        isMaxPlayer = maxPlayer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void addChild(Node child) {
        children.add(child);
    }
}

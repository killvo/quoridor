package com.quebec.core.domains.bot;

import com.quebec.core.domains.board.BoardService;
import com.quebec.core.domains.move.Move;
import com.quebec.core.domains.move.MovePlayerMove;
import com.quebec.core.domains.move.PlaceWallMove;
import com.quebec.core.domains.player.model.Player;
import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MiniMax {
    public BoardStateTree tree;
    public BoardService boardService;
    public Evaluator evaluator;

    public void constructTree(Graph<String, DefaultEdge> board, Player player) {
        tree = new BoardStateTree();
        Node root = new Node();
        root.setBoard(board);
        tree.setRoot(root);
        constructTree(root, player, 0);
    }

    private void constructTree(Node parentNode, Player player, int counter) {
        List<MovePlayerMove> listOfPossibleMovePlayerMoves = boardService.getAllPossibleMakeMoves(
                parentNode.getBoard(),
                ((MovePlayerMove) parentNode.getMoveThatLeadToIt()).toRequest(player.getId()),
                parentNode.getPlayerPositions()
        );
        List<PlaceWallMove> listOfPossiblePlaceWallMoves = boardService.getAllPossiblePlaceWalls(
                parentNode.getBoard(),
                parentNode.getWalls(),
                ((PlaceWallMove) parentNode.getMoveThatLeadToIt()).toRequest(player.getId()),
                parentNode.getPlayerPositions()
        );

        List<Move> list = new ArrayList<>();
        list.addAll(listOfPossibleMovePlayerMoves);
        list.addAll(listOfPossiblePlaceWallMoves);

        list.forEach(n -> {
            Node newNode = new Node();
            newNode.setMoveThatLeadToIt(n);
            if (n.getClass().getName().equals("MovePlayerMove")) {
                newNode.setBoard((SimpleGraph) ((AbstractBaseGraph) parentNode.getBoard()).clone());
            } else {
                newNode.setBoard(boardService.placeWallImmutable(((PlaceWallMove) n).getX_corner(), ((PlaceWallMove) n).getY_corner(), ((PlaceWallMove) n).getOrientation()));
            }
            newNode.setWalls(parentNode.getWalls());
            newNode.setPlayerPositions(parentNode.playerPositions);
            newNode.setScore(evaluator.evaluate(newNode, player));
            parentNode.addChild(newNode);
            if (counter < 2) {
                constructTree(newNode, player, counter+1);
            }
        });
    }

    public BoardStateTree getTree() {
        return tree;
    }
}

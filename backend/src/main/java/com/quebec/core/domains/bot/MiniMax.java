package com.quebec.core.domains.bot;

import com.quebec.core.domains.board.BoardService;
import com.quebec.core.domains.move.Move;
import com.quebec.core.domains.move.MovePlayerMove;
import com.quebec.core.domains.move.PlaceWallMove;
import org.jgrapht.Graph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class MiniMax {
    public BoardStateTree tree;
    public BoardService boardService;

    public void constructTree(Graph<String, DefaultEdge> board, Map<UUID, String> playerPositions) {
        tree = new BoardStateTree();
        Node root = new Node();
        root.setBoard(board);
        tree.setRoot(root);
        constructTree(root, 0);
    }

    private void constructTree(Node parentNode, int counter) {
        List<MovePlayerMove> listOfPossibleMovePlayerMoves = boardService.getAllPossibleMakeMoves(parentNode.getBoard(), ((MovePlayerMove) parentNode.getMoveThatLeadToIt()).toRequest(), parentNode.getPlayerPositions());
        List<PlaceWallMove> listOfPossiblePlaceWallMoves = boardService.getAllPossiblePlaceWalls(parentNode.getBoard(), parentNode.getWalls(), ((PlaceWallMove) parentNode.getMoveThatLeadToIt()).toRequest(), parentNode.getPlayerPositions());

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
            // TODO Create an evaluation function and result put in next line
            newNode.setScore(0);
            parentNode.addChild(newNode);
            if (counter < 2) {
                constructTree(newNode, counter+1);
            }
        });
    }

    public BoardStateTree getTree() {
        return tree;
    }
}

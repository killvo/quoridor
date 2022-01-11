package com.quebec.core.domains.bot;

import com.quebec.core.domains.bot.dto.BoardStateForBotDto;
import com.quebec.core.domains.bot.dto.BotResponse;
import com.quebec.core.domains.bot.exceptions.InvalidBoardStateException;
import com.quebec.core.domains.move.Move;
import com.quebec.core.domains.move.MovePlayerMove;
import com.quebec.core.domains.move.PlaceWallMove;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class BotPlayer {
    //private BoardService boardService;
    //private MoveValidator moveValidator;
    private MiniMax miniMax;

    public BotResponse getBotMove(BoardStateForBotDto boardState) {
        if (boardState == null) {
            throw new InvalidBoardStateException("Board state incorrect!");
        }
        miniMax.constructTree(boardState.getBoard(), boardState.getPlayer());
        Move move = getBestPossibleMove();
        if (move.getClass().getName().equals("PlaceWallMove")) {
            return BotResponse.from((PlaceWallMove) move);
        } else {
            return BotResponse.from((MovePlayerMove) move);
        }
    }

    private Node findBestChild(boolean isMaxPlayer, List<Node> children) {
        Comparator<Node> byScoreComparator = Comparator.comparing(Node::getScore);
        return children.stream()
                .max(isMaxPlayer ? byScoreComparator : byScoreComparator.reversed())
                .orElseThrow(NoSuchElementException::new);
    }

    public Move getBestPossibleMove() {
        Node root = miniMax.getTree().getRoot();
        getBestPossibleMove(root);
        return root.getMoveThatLeadToIt();
    }

    private void getBestPossibleMove(Node node) {
        List<Node> children = node.getChildren();
        boolean isMaxPlayer = node.isMaxPlayer();
        Node bestChild = findBestChild(isMaxPlayer, children);
        node.setScore(bestChild.getScore());
        node.setMoveThatLeadToIt(bestChild.moveThatLeadToIt);
    }
}

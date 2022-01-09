package com.quebec.core.cli;

import com.quebec.core.cli.model.Command;
import com.quebec.core.cli.model.PlayerColor;
import com.quebec.core.cli.model.XTileLetter;
import com.quebec.core.cli.model.XWallLetter;
import com.quebec.core.domains.initializer.GameInitService;
import com.quebec.core.domains.move.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class CommandLineHandler implements CommandLineRunner {

    private final ApplicationContext appContext;
    private final GameInitService gameInitService;
    private final GameService gameService;

    /*

    чорний гравець знаходиться зверху (клітинка E1), а білий - знизу (E9)

     */


    @Override
    public void run(String... args) {
        boolean isStop = false;
        Scanner scanner = new Scanner(System.in);
        while (!isStop) {
            System.out.print("-> ");
            String commandName = scanner.nextLine().trim();
            if (commandName.equals("stop")) {
                isStop = true;
                stopCommandReader();
                return;
            }

            String[] commandParts = commandName.split(" ");
            Command command = Command.valueOf(commandParts[0].toUpperCase(Locale.ROOT));
            switch (command) {
                case BLACK:
                    handleColorCommand(PlayerColor.BLACK);
                    break;
                case WHITE:
                    handleColorCommand(PlayerColor.WHITE);
                    break;
                case MOVE:
                    handleMoveCommand(commandParts);
                    break;
                case JUMP:
                    break;
                case WALL:
                    break;
                default:
                    out("Command not found");
                    break;
            }

        }
    }

    private void stopCommandReader() {
        out("Command reader stopped");
        initiateShutdown(0);
    }

    private void out(String response) {
        System.out.println("<- " + response);
    }

    public void initiateShutdown(int returnCode){
        SpringApplication.exit(appContext, () -> returnCode);
    }

    private void handleColorCommand(PlayerColor playerColor) {
        // TODO: gameInitService.setPlayerColor();

        if (playerColor.equals(PlayerColor.WHITE)) {
            // TODO: Move move = gameService.getBotPlayerMove();
            out("Bot move");
        }
    }

    private void handleMoveCommand(String[] commandParts) {
        validateCommand(commandParts);
        String position = commandParts[1];
        String[] positionArray = position.split("");
        String xLetter = positionArray[0];
        int x = xTilePositionFromLetter(xLetter);
        int y = Integer.parseInt(positionArray[1]);
        // TODO: enemy player move: gameService.makeMove(x, y);
        String botResponse = getBotResponse();
        out(botResponse);
    }

    private void validateCommand(String[] commandParts) {
        int length = commandParts.length;
        if (length != 2) {
            out("Incorrect command");
        }
    }

    private String getBotResponse() {
        // TODO: our bot player response: Move move = gameService.getBotPlayerMove();
        // Orientation orientation = move.getOrientation();
        // String xResponse = orientation == null ? tileLetterFromX(move.getX()) : wallLetterFromX(move.getX());
        // int yResponse = move.getY();
        // String response = move.getType().toString() + " " + xResponse + yResponse;
        // if (orientation != null) response += "orientation.getLetter()";
        return "response";
    }

    private int xTilePositionFromLetter(String letter) {
        return XTileLetter.valueOf(letter).getX();
    }

    private int xWallPositionFromLetter(String letter) {
        return XWallLetter.valueOf(letter).getX();
    }

    private String tileLetterFromX(int x) {
        XTileLetter xTileLetter = XTileLetter.xLetterByX(x);
        if (xTileLetter == null) {
            throw new RuntimeException("There are no letter for this position");
        }
        return xTileLetter.toString();
    }

    private String wallLetterFromX(int x) {
        XWallLetter xWallLetter = XWallLetter.xLetterByX(x);
        if (xWallLetter == null) {
            throw new RuntimeException("There are no letter for this position");
        }
        return xWallLetter.toString();
    }
}

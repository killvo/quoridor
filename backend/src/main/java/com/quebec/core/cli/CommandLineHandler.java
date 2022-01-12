package com.quebec.core.cli;

import com.quebec.core.cli.model.Command;
import com.quebec.core.cli.model.PlayerColor;
import com.quebec.core.cli.model.XTileLetter;
import com.quebec.core.cli.model.XWallLetter;
import com.quebec.core.domains.bot.dto.BotResponse;
import com.quebec.core.domains.initializer.GameInitService;
import com.quebec.core.domains.initializer.dto.GameStartWithBotResponse;
import com.quebec.core.domains.move.GameService;
import com.quebec.core.domains.move.MovePlayerMove;
import com.quebec.core.domains.move.PlaceWallMove;
import com.quebec.core.domains.move.model.Orientation;
import com.quebec.core.domains.player.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommandLineHandler implements CommandLineRunner {

    private final ApplicationContext appContext;
    private final GameInitService gameInitService;
    private final GameService gameService;

    private Player botPlayer;
    private Player enemyPlayer;

    /*

    чорний гравець знаходиться зверху (клітинка E1), а білий - знизу (E9)
    player1 - up
    player2 - bottom

    ! не забувати що у консоль виводимо координати 1..9, а на дошці використовуємо 0..8

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
                    handleJumpCommand(commandParts);
                    break;
                case WALL:
                    handleWallCommand(commandParts);
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
        GameStartWithBotResponse response = gameInitService.startGameAndSetBotPlayerColor(playerColor);
        botPlayer = response.getBotPlayer();
        enemyPlayer = response.getEnemyPlayer();

        if (playerColor.equals(PlayerColor.WHITE)) {
            BotResponse botResponse = gameService.getBotMove();
            String responseLine;
            if (botResponse.getOrientation() == Orientation.NONE) {
                String x = tileLetterFromX(convertCoordinateToResponse(botResponse.getXCorner()));
                int y = convertCoordinateToResponse(botResponse.getYCorner());
                responseLine = "move " + x + y;
            } else {
                String x = wallLetterFromX(convertCoordinateToResponse(botResponse.getXCorner()));
                int y = convertCoordinateToResponse(botResponse.getYCorner());
                String orientationLetter = botResponse.getOrientation() == Orientation.HORIZONTAL ? "h" : "v";
                responseLine = "wall " + x + y + orientationLetter;
            }
            out(responseLine);
        }
    }

    private void handleMoveCommand(String[] commandParts) {
        validateCommand(commandParts);
        String position = commandParts[1];
        String[] positionArray = position.split("");
        String xLetter = positionArray[0];
        int x = xTilePositionFromLetter(xLetter);
        int y = convertCoordinateToResponse(Integer.parseInt(positionArray[1]));
        var moveRequest = new MovePlayerMove(x, y).toRequest(enemyPlayer.getId());
        gameService.makeMove(moveRequest);
        String botResponse = getBotResponse();
        out(botResponse);
    }

    private void handleJumpCommand(String[] commandParts) {
        validateCommand(commandParts);
        String position = commandParts[1];
        String[] positionArray = position.split("");
        String xLetter = positionArray[0];
        int x = xTilePositionFromLetter(xLetter);
        int y = convertCoordinateToResponse(Integer.parseInt(positionArray[1]));
        var jumpRequest = new MovePlayerMove(x, y).toRequest(enemyPlayer.getId());
        gameService.makeJump(jumpRequest);
        String botResponse = getBotResponse();
        out(botResponse);
    }

    private void handleWallCommand(String[] commandParts) {
        validateCommand(commandParts);
        String position = commandParts[1];
        String[] positionArray = position.split("");
        String xLetter = positionArray[0];
        int x = xWallPositionFromLetter(xLetter);
        int y = convertCoordinateToResponse(Integer.parseInt(positionArray[1]));
        String orientationString = positionArray[2];
        Orientation orientation = orientationString.equals("v") ? Orientation.VERTICAL : Orientation.HORIZONTAL;
        var placeWallRequest = new PlaceWallMove(x, y, orientation).toRequest(enemyPlayer.getId());
        gameService.placeWall(placeWallRequest);
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
        BotResponse botResponse = gameService.getBotMove();
        Orientation orientation = botResponse.getOrientation();
        String xResponse = orientation == Orientation.NONE
                ? tileLetterFromX(convertCoordinateToResponse(botResponse.getXCorner()))
                : wallLetterFromX(convertCoordinateToResponse(botResponse.getXCorner()));
        int yResponse = convertCoordinateToResponse(botResponse.getYCorner());
        // TODO: implement type of move (move, jump, wall) String response = move.getType().toString() + " " + xResponse + yResponse;
        String moveName = orientation == null ? "move" : "wall";
        String orientationLetter = orientation == Orientation.HORIZONTAL ? "h" : "v";
        String response = moveName + " " + xResponse + yResponse;
        if (orientation != Orientation.NONE) response += orientationLetter;
        return response;
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

    private int convertCoordinateToResponse(int coordinate) {
        return coordinate + 1;
    }
}

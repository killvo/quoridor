package com.quebec.core.cli;

import com.quebec.core.cli.model.Command;
import com.quebec.core.cli.model.PlayerColor;
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
        // TODO: invocate method that set our bot player color: gameInitService.setPlayerColor();

        if (playerColor.equals(PlayerColor.WHITE)) {
            // TODO: invocate method that make bot move: gameService.getBotPlayerMove();
            out("Bot move");
        }
    }

    private void handleMoveCommand(String[] commandParts) {
        validateCommand(commandParts);
        String position = commandParts[1];
        String[] positionArray = position.split("");
        String xLetter = positionArray[0];
        int y = Integer.parseInt(positionArray[1]);
    }

    private void validateCommand(String[] commandParts) {
        int length = commandParts.length;
        if (length != 2) {
            out("Incorrect command");
        }
    }
}

package com.quebec.core.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLineHandler implements CommandLineRunner {

    @Autowired
    private ApplicationContext appContext;

    @Override
    public void run(String... args) {
        boolean isStop = false;
        Scanner scanner = new Scanner(System.in);
        while (!isStop) {
            System.out.print("-> ");
            String command = scanner.nextLine();
            if (command.equals("stop")) {
                isStop = true;
                stopCommandReader();
                return;
            }

            System.out.println("<- You entered: " + command);
        }
    }

    private void stopCommandReader() {
        System.out.println("<- Command reader stopped");
        initiateShutdown(0);
    }

    public void initiateShutdown(int returnCode){
        SpringApplication.exit(appContext, () -> returnCode);
    }
}

package com.vgoryashko.consolechat;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Class that implements console chat. User enters some messages in the console and random replays are read from the ./answers.txt file.
 * All messages are loged into the ./log.txt file.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 2/13/17
 */
public class ConsoleChat {

    /**
     * Method that implements basic logic of the program.
     */
    public void chat() {
        File answers = new File(String.format(".%schapter_003%sanswers.txt", File.separator, File.separator));
        File log = new File(String.format(".%schapter_003%slog.txt", File.separator, File.separator));
        SplitFileToArray splitFileToArray = new SplitFileToArray();
        boolean pause = false;
        String message;
        String response;
        int random;

        if (log.exists()) {
            log.delete();
        }

        try (RandomAccessFile logFile = new RandomAccessFile(log, "rw");
             Scanner scanner = new Scanner(System.in)) {

            String[] answersArray = splitFileToArray.splitFile(answers);
            System.out.println("You can enter a message, if you want pause system responses type \"pause\", to continue \"resume\" or \"exit\" to exit from the chat.");
            logFile.seek(0);

            while (true) {

                message = scanner.nextLine();

                if (message.toLowerCase().equals("exit")) {
                    System.out.println("Exiting from the chat.");
                    System.exit(0);
                } else if (message.toLowerCase().equals("pause")) {
                    pause = true;
                } else if (message.toLowerCase().equals("resume")) {
                    pause = false;
                }

                logFile.write(message.concat(System.getProperty("line.separator")).getBytes());

                if (!pause) {
                    Random indexOfPhrase = new Random();
                    random = indexOfPhrase.nextInt(answersArray.length);
                    response = answersArray[random];
                    System.out.printf("%s", response.concat(System.getProperty("line.separator")));
                    logFile.write(response.concat(System.getProperty("line.separator")).getBytes());
                }
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Main method of the application.
     * @param args                          standard parameter for main() method
     */
    public static void main(String[] args) {
        new ConsoleChat().chat();
    }

}

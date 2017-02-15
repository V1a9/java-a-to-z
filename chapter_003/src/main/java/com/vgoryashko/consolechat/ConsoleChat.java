package com.vgoryashko.consolechat;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Class that implements console chat. User enters some messages in the console and random replays are read from the ./answers.txt file.
 * All messages are logged into the ./log.txt file.
 *
 * @author Vlad Goryashko
 * @version 0.7
 * @since 2/15/17
 */
public class ConsoleChat {

    /**
     * Constant that defines exit parameter.
     */
    protected static final String EXIT = "exit";

    /**
     * Constant that defines pause parameter.
     */
    protected static final String PAUSE = "pause";

    /**
     * Constant that defines resume parameter.
     */
    protected static final String RESUME = "resume";

    /**
     * Variable that stores input stream.
     */
    private InputStream input;

    /**
     * Variable that stores a path to a file with responses.
     */
    private File answers;

    /**
     * Coonstructor for the class.
     *
     * @param aInput                    Input stream to be used
     * @param aAnswers                  File with answers for a bot
     */
    public ConsoleChat(InputStream aInput, File aAnswers) {
        this.input = aInput;
        this.answers = aAnswers;
    }

    /**
     * Method that implements basic logic of the program.
     */
    public void chat() {
        File log = new File(String.format(".%slog.txt", File.separator));
        SplitFileToArray splitFileToArray = new SplitFileToArray();
        boolean pause = false;
        String message;
        String response;
        int random;

        if (log.exists()) {
            log.delete();
        }

        try (RandomAccessFile logFile = new RandomAccessFile(log, "rw");
             Scanner scanner = new Scanner(input)) {
            log.getCanonicalPath();
            String[] answersArray = splitFileToArray.splitFile(answers);
            System.out.println("You can enter a message, if you want pause system responses type \"pause\", to continue \"resume\" or \"exit\" to exit from the chat.");
            logFile.seek(0);

            do {

                message = scanner.nextLine();

                if (message.toLowerCase().equals(EXIT)) {
                    System.out.println("Exiting from the chat.");
                } else if (message.toLowerCase().equals(PAUSE)) {
                    System.out.println("pause");
                    pause = true;
                } else if (message.toLowerCase().equals(RESUME)) {
                    System.out.println("resume");
                    pause = false;
                }

                logFile.write(message.concat(System.getProperty("line.separator")).getBytes());

                if (!pause && !message.toLowerCase().equals("exit")) {
                    Random indexOfPhrase = new Random();
                    random = indexOfPhrase.nextInt(answersArray.length);
                    response = answersArray[random];
                    System.out.printf("%s", response.concat(System.getProperty("line.separator")));
                    logFile.write(response.concat(System.getProperty("line.separator")).getBytes());
                }
            } while (!message.equals("exit"));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Main method of the application.
     * @param args                          standard parameter for main() method
     */
    public static void main(String[] args) {
        new ConsoleChat(System.in, new File(String.format(".%sanswers.txt", File.separator))).chat();
    }

}

package com.vgoryashko.search;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

/**
 * Class that performs search of a file in a given directory.
 *
 * @author Vlad Goryashko
 * @version 1.1
 * @since 4/9/17
 */
public class Search {

    /**
     * Variable that store a system file separator.
     */
    private final String fs = File.separator;

    /**
     * Variable that referring to a search directory path.
     */
    private Path searchDir = null;

    /**
     * Variable that stores a name of a file to be searched.
     */
    private String searchFile = null;

    /**
     * Variable that stores a path of temp directory.
     */
    private Path tmp = Paths.get(String.format(".%stmp%s", fs, fs));

    /**
     * Variable that referring to a log file path.
     */
    private Path logFile = null;

    /**
     * Method that check necessity to write a result of the program to log, performs searching of a file.
     * @param userInput                             command of a user
     * @param aWriteLog                             key that defines if write log is required
     * @throws IOException                          IOException
     */
    public void search(String userInput, boolean aWriteLog) throws IOException {

        SimpleFileVisitor findFile;

        if ("-m".equals(userInput)) {
            findFile = new SearchFileByGlob(this.searchFile, this.logFile, aWriteLog);
            Files.walkFileTree(this.searchDir, findFile);
        } else if ("-f".equals(userInput)) {
            findFile = new SearchFileByName(this.searchFile, this.logFile, aWriteLog);
            Files.walkFileTree(this.searchDir, findFile);
        }
    }

    /**
     * Method that implements main logic of the application.
     * @param userInput                     user input from the console
     * @throws IOException                  IOException
     */
    public void start(String[] userInput) throws IOException {

        if (tmp.toFile().exists()) {
            File[] files = tmp.toFile().listFiles();
            for (File file : files) {
                file.delete();
            }
            tmp.toFile().delete();
        }

        this.searchDir = Paths.get(userInput[1]);
        this.searchFile = userInput[3];

        if (userInput.length > 5) {

            this.logFile = Paths.get(String.format("%s%s%s", tmp.toAbsolutePath().toString(), fs, userInput[6]));

            if (!Files.exists(this.logFile)) {
                try {
                    Files.createDirectory(tmp);
                    Files.createFile(logFile);
                } catch (IOException ioe) {
                    System.err.format("Exception: %s", ioe);
                }
            }

            search(userInput[4], true);

        } else {
            search(userInput[4], false);
        }

    }

    /**
     * Main method the entry point.
     * @param args                          standard parameter for the main() method
     * @throws IOException                  IOException
     */
    public static void main(String[] args) throws IOException {
        Search search = new Search();
        KeysValidator keysValidator = new KeysValidator(args);

        if (keysValidator.validate()) {
            search.start(args);
        } else {
            System.exit(-1);
        }
    }
}

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
 * @since 4.3.2017
 * @version 0.6
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
    private Path tmp = Paths.get(String.format("..%sauxiliary%stmp", fs, fs));

    /**
     * Variable that referring to a log file path.
     */
    private Path logFile = null;

    /**
     * Variable that is stores a write/not write to log key.
     */
    private boolean writeLog = false;

    /**
     * Method that prints help in the console.
     */
    public void printHelp() {

        System.out.println("Format of a command is the following:");
        System.out.println("Key \"-d\" followed by a name of a dir where a file to be searched.");
        System.out.println("Key \"-n\" followed by a name of a file to be searched.");
        System.out.println("Key \"-m\" a file to be searched by a mask.");
        System.out.println("Key \"-f\" a file to be searched by a full name.");
        System.out.println("Key \"-o\" write result to a file followed by a name of a log file.");
        System.out.println("Example: -d C:\\ -n test.txt -f -o C:\\tmp\\log.txt\n");
        System.out.println();
    }

    /**
     * Method that implements main logic of the application.
     * @param userInput                     user input from the console
     * @throws IOException                  IOException
     */
    public void start(String[] userInput) throws IOException {

        System.out.println("Program that searches a file in a directory.");
        System.out.println("----------------------------------------------");
        printHelp();

        SimpleFileVisitor searchFile = null;

        if (tmp.toFile().exists()) {
            File[] files = tmp.toFile().listFiles();
            for (File file : files) {
                file.delete();
            }
            tmp.toFile().delete();
        }

        if ("-d".equals(userInput[0])) {
            this.searchDir = Paths.get(userInput[1]);
        } else {
            System.out.println("Wrong key entered. Must be \"-d\". Try again.");
        }

        if ("-n".equals(userInput[2])) {
            this.searchFile = userInput[3];
        } else {
            System.out.println("Wrong key entered. Must be \"-n\". Try again.");
        }

        if (userInput[5] != null && "-o".equals(userInput[5])) {

            this.logFile = Paths.get(String.format("%s%s%s", tmp.toString(), fs, userInput[6]));

            if (!Files.exists(this.logFile)) {
                try {
                    Files.createDirectory(tmp);
                    Files.createFile(logFile);
                } catch (IOException ioe) {
                    System.err.format("Exception: %s", ioe);
                }
            }
            writeLog = true;
        } else if (userInput[5] != null && !"-o".equals(userInput[5])) {
            System.out.println("Wrong key entered. Must be \"-o\". Try again.");
        }

        if ("-m".equals(userInput[4])) {
            searchFile = new SearchFileByGlob(this.searchFile, this.logFile, this.writeLog);
        } else if ("-f".equals(userInput[4])) {
            searchFile = new SearchFileByName(this.searchFile, this.logFile, this.writeLog);
        } else {
            System.out.println("Wrong key entered. Must be \"-m or -f\". Try again.");
        }

        Files.walkFileTree(this.searchDir, searchFile);
        this.writeLog = false;
    }

    /**
     * Main method the entry point.
     * @param args                          standard parameter for the main() method
     * @throws IOException                  IOException
     */
    public static void main(String[] args) throws IOException {

           new Search().start(args);

    }
}

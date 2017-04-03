package com.vgoryashko.searchfile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

/**
 * Class that performs search of a file in a given directory.
 *
 * @author Vlad Goryashko
 * @since 4.3.2017
 * @version 0.6
 */
public class StartApp {

    private String fs = File.separator;

    private Path searchDir = null;
    private String searchFile = null;
    private Path tmp = Paths.get(String.format(".%schapter_003%stmp", fs, fs));
    private Path logFile = null;
    private boolean misprint = false;
    private boolean writeLog = false;
    private String searchType = null;

    public void printHelp() {

        System.out.println("Format of a command is the following:");
        System.out.println("Key \"-d\" followed by a name of a dir where a file to be searched.");
        System.out.println("Key \"-n\" followed by a name of a file to be searched.");
        System.out.println("Key \"-m\" a file to be searched by a mask.");
        System.out.println("Key \"-f\" a file to be searched by a full name.");
        System.out.println("Key \"-o\" write result to a file followed by a name of a log file.");
        System.out.println("Type \"help\" to see a help menu.");
        System.out.println("Type \"exit\" to exit from the program.\n");
        System.out.println("Example: -d C:\\ -n test.txt -f -o C:\\tmp\\log.txt\n");
        System.out.println();
    }

    public String[] splitCommand(String command) {
        String[] result = command.split("-");
        return result;
    }

    public void retrievePaths(String[] commandSplit) {

        if (commandSplit[1].startsWith("d")) {
            this.searchDir = Paths.get(commandSplit[1].substring(1).trim());
        } else {
            System.out.println("Wrong key entered. Must be \"-d\". Try again.");
            misprint = true;
        }

        if (commandSplit[2].startsWith("n")) {
            this.searchFile = commandSplit[2].substring(1).trim();
        } else {
            System.out.println("Wrong key entered. Must be \"-n\". Try again.");
            misprint = true;
        }

        if (commandSplit[3].startsWith("m")) {
            searchType = "m";
        } else if (commandSplit[3].startsWith("f")) {
            searchType = "f";
        } else {
            System.out.println("Wrong key entered. Must be \"-m or -f\". Try again.");
            misprint = true;
        }

        if (commandSplit.length == 5 && commandSplit[4].startsWith("o")) {

            this.logFile = Paths.get(String.format("%s%s%s", tmp.toString(), fs, commandSplit[4].substring(1).trim()));

            if (!Files.exists(this.logFile)) {
                try {
                    Files.createDirectory(tmp);
                    Files.createFile(logFile);
                } catch (IOException ioe) {
                    System.err.format("Exception: %s", ioe);
                }
            }
            writeLog = true;

        } else if (commandSplit.length == 5 && !commandSplit[4].startsWith("o")) {
            System.out.println("Wrong key entered. Must be \"-o\". Try again.");
            misprint = true;
        }
    }

    public void start(Scanner scanner) throws IOException {

        System.out.println("Program that searches a file in a directory.");
        System.out.println("----------------------------------------------");
        printHelp();

        SimpleFileVisitor searchFile = null;

        String command;
        String[] commandSplit = null;

        if (tmp.toFile().exists()) {
            File[] files = tmp.toFile().listFiles();
            for (File file : files) {
                file.delete();
            }
            tmp.toFile().delete();
        }

        do {

            System.out.println("Enter a command: ");

            command = scanner.nextLine();

            if (!"exit".equals(command)) {
                if ("help".equals(command)) {
                    printHelp();
                } else {
                    commandSplit = splitCommand(command);
                }

                retrievePaths(commandSplit);

                if ("m".equals(searchType)) {
                    searchFile = new SearchFileByGlob(this.searchFile, this.logFile, this.writeLog);
                } else if ("f".equals(searchType)) {
                    searchFile = new SearchFileByName(this.searchFile, this.logFile, this.writeLog);
                }

                Files.walkFileTree(this.searchDir, searchFile);
                this.writeLog = false;
            }

        } while (!"exit".equals(command));
    }

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {

           new StartApp().start(scanner);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }
}

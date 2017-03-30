package com.vgoryashko.searchfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class that performs search of a file in a given directory.
 *
 * @author Vlad Goryashko
 * @since 30.03.2017
 * @version 0.3
 */
public class SearchFile {

    private String fs = File.separator;
    private Path tmp = Paths.get(String.format(".%schapter_003%stmp", fs, fs));
    private Path searchDir = null;
    private Path searchFile = null;
    private Path logFile = null;

    public void printHelp() {

        System.out.println("Format of a command is the following:");
        System.out.println("Key \"-d\" followed by a name of a dir where a file to be searched.");
        System.out.println("Key \"-n\" followed by a name of a file to be searched.");
        System.out.println("Key \"-m\" a file to be searched by a mask.");
        System.out.println("Key \"-f\" a file to be searched by a full name.");
        System.out.println("Key \"-o\" write result to a file followed by a name of a log file.");
        System.out.println("Example: -d C:\\ -n test.txt -f -o C:\\tmp\\log.txt\n");
        System.out.println("Type \"help\" to see a help menu.");
        System.out.println("Type \"exit\" to exit from the program.");
        System.out.println();
    }

    public String[] splitCommand(String command) {
        String[] result = null;
        result = command.split("-");
        return result;
    }

    public void retrievePaths(String[] commandSplit) {

        if (commandSplit[0].startsWith("d")) {
            this.searchDir = Paths.get(commandSplit[0].substring(1).trim());
        } else {
            System.out.println("Wrong key entered. Must be \"-d\". Try again.");
        }

        if (commandSplit[1].startsWith("n")) {
            this.searchFile = Paths.get(commandSplit[1].substring(1).trim());
        } else {
            System.out.println("Wrong key entered. Must be \"-n\". Try again.");
        }

        if (commandSplit[3].startsWith("o")) {
            this.logFile = Paths.get(commandSplit[3].substring(1).trim());
        } else {
            System.out.println("Wrong key entered. Must be \"-o\". Try again.");
        }
    }

    public void start() {

        Charset charset = Charset.forName("US-ASCII");

//        if (tmp.toFile().exists()) {
//            File[] files = tmp.toFile().listFiles();
//            for (File file : files) {
//                file.delete();
//            }
//            tmp.toFile().delete();
//        }

        try {
            Files.createDirectory(tmp);
            Files.createFile(logFile);
        } catch (IOException ioe) {
            System.err.format("Exception: %s", ioe);
        }

        System.out.println("Program that searches a file in a directory.");
        System.out.println("----------------------------------------------");
        printHelp();

        String command = "";
        String[] commandSplit = null;

        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter out = Files.newBufferedWriter(logFile, charset)) {

            while (!("exit".equals(command))) {
                System.out.println("Enter a command: ");
                command = scanner.nextLine();
                if ("help".equals(command)) {
                    printHelp();
                } else {
                    commandSplit = splitCommand(command);
                }
            }

            retrievePaths(commandSplit);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SearchFile().start();
    }
}

package com.vgoryashko.searchfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

/**
 * Class that performs search of a file in a given directory.
 *
 * @author Vlad Goryashko
 * @since 31.03.2017
 * @version 0.5
 */
public class SearchFile extends SimpleFileVisitor {

    private SearchFile srch;
    private String fs = File.separator;
    private Path tmp = Paths.get(String.format(".%schapter_003%stmp", fs, fs));
    private Path searchDir = null;
    private Path searchFile = null;
    private Path logFile = null;
    private boolean misprint = false;
    private boolean writeLog = false;

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {

        String pattern = this.searchFile.getFileName().toString();
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        Path name = path.getFileName();
        if (name != null && matcher.matches(name)) {
            System.out.println(name.toString());
        }
        return FileVisitResult.CONTINUE;
    }

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
            this.searchFile = Paths.get(commandSplit[2].substring(1).trim());
        } else {
            System.out.println("Wrong key entered. Must be \"-n\". Try again.");
            misprint = true;
        }

        if (commandSplit.length == 5 && commandSplit[4].startsWith("o")) {
            this.logFile = Paths.get(commandSplit[4].substring(1).trim());
            writeLog = true;
        } else if (commandSplit.length == 5 && !commandSplit[4].startsWith("o")) {
            System.out.println("Wrong key entered. Must be \"-o\". Try again.");
            misprint = true;
        }
    }

    public void search(Path searchDir, Path searchFile) throws IOException {
        Files.walkFileTree(searchDir, srch);
    }

    public void writeLog(Path searchResult) {

        Charset charset = Charset.forName("US-ASCII");

        if (tmp.toFile().exists()) {
            File[] files = tmp.toFile().listFiles();
            for (File file : files) {
                file.delete();
            }
            tmp.toFile().delete();
        }

        try {
            Files.createDirectory(tmp);
            Files.createFile(logFile);
        } catch (IOException ioe) {
            System.err.format("Exception: %s", ioe);
        }

        try (BufferedWriter out = Files.newBufferedWriter(logFile, charset)) {
            out.write(searchResult.toString(), 0, searchResult.toString().length());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public void start(Scanner scanner) {

        System.out.println("Program that searches a file in a directory.");
        System.out.println("----------------------------------------------");
        printHelp();

        String command;
        String[] commandSplit = null;


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

                if (misprint) {
                    continue;
                } else {
                    if (writeLog) {
                        writeLog(search(searchDir, searchFile));
                    } else {
                        System.out.println(search(searchDir, searchFile).toString());
                    }
                }
            }
        } while (!"exit".equals(command));
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

           srch = new SearchFile();
           srch.start(scanner);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }
}

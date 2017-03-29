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
 * Created by vgoryash on 28.03.2017.
 */
public class SearchFile {

    public void start() {

        String fs = File.separator;
        Path tmp = Paths.get(String.format(".%schapter_003%stmp", fs, fs));
        Path logFile = Paths.get(String.format(".%schapter_003%stmp%slog.txt", fs, fs, fs));
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

        String command = "";
        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter out = Files.newBufferedWriter(logFile, charset)) {

            while (!("exit".equals(command))) {
                System.out.println("Enter a command: ");
                command = scanner.nextLine();
                String[] commandSplit = command.split(" ");
                Path searchDir = Paths.get(commandSplit[0]);
                out.write(searchDir.toString());
                out.newLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SearchFile().start();
    }
}

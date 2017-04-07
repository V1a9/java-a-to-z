package com.vgoryashko.search;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Class that writes files' names to a file.
 *
 * @author Vlad Goryashko
 * @version 1.0
 * @since 4/7/17
 */
public class WriteLog {

    /**
     * Variable that referring to a logFile path.
     */
    private Path logFile;

    /**
     * Variable that referring to a path of a file.
     */
    private Path searchFile;

    /**
     * Variable that defines a charset for a write method.
     */
    private final Charset charset = Charset.forName("US-ASCII");

    /**
     * Constructor for the class.
     * @param aLogFile                  referring to a logFile path
     * @param aSearchFile               referring to a path of a file
     */
    public WriteLog(Path aLogFile, Path aSearchFile) {
        this.logFile = aLogFile;
        this.searchFile = aSearchFile;
    }

    /**
     * Method that writes data into a log file.
     */
    public void write() {
        try (BufferedWriter out = Files.newBufferedWriter(logFile, charset, StandardOpenOption.APPEND)) {
            out.write(searchFile.toString());
            out.newLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

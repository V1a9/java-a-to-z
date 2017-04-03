package com.vgoryashko.searchfile;

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
 * @version 0.6
 * @since 4/3/17
 */
public class WriteLog {

    private Path logFile;
    private Path searchFile;
    private final Charset charset = Charset.forName("US-ASCII");

    public WriteLog(Path aLogFile, Path aSearchFile) {
        this.logFile = aLogFile;
        this.searchFile = aSearchFile;
    }

    public void write() {
        try (BufferedWriter out = Files.newBufferedWriter(logFile, charset, StandardOpenOption.APPEND)) {
            out.write(searchFile.toString());
            out.newLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

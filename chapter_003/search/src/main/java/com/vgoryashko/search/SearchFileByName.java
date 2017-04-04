package com.vgoryashko.search;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Class that searches a file by name.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 4/3/17
 */
public class SearchFileByName extends SimpleFileVisitor<Path> {

    /**
     * Variable that is referring to PathMatcher.
     */
    private String fileName;

    /**
     * Variable that referring to a logFile path.
     */
    private Path logFile;

    /**
     * Variable that is stores a write/not write to log key.
     */
    private boolean writeLog;

    /**
     * Constructor for the class.
     * @param aFileName                             String that represents a file name which will be used for file search
     * @param aLogFile                              log file path where to write a result
     * @param aWriteLog                             boolean key to check write of not write a log
     */
    public SearchFileByName(String aFileName, Path aLogFile, boolean aWriteLog) {
        this.fileName = aFileName;
        this.logFile = aLogFile;
        this.writeLog = aWriteLog;
    }

    /**
     * Method that performs searching of files based on glob syntax.
     * @param file                              file from a file tree to be checked against a pattern
     * @param logFile                           log file path where to write a result
     * @param writeLog                          boolean key to check write of not write a log
     */
    public void find(Path file, Path logFile, boolean writeLog) {

        Path name = file.getFileName();

        if (name != null && name.toString().equals(this.fileName)) {

            if (writeLog) {
                new WriteLog(logFile, file).write();
            } else {
                System.out.println(file);
            }
        }
    }


    /**
     * Invoked for a file in a directory.
     * <p>
     * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
     * CONTINUE}.
     *
     * @param file                      a file to be checked against a pattern
     * @param attrs                     file attributes
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        find(file, this.logFile, this.writeLog);
        return FileVisitResult.CONTINUE;
    }
}

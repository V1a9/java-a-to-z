package com.vgoryashko.search;

import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.PathMatcher;
import java.nio.file.FileSystems;
import java.nio.file.attribute.BasicFileAttributes;

import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Class that implements searches a file in a file tree and writes a result in a file.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 04.04.2017
 */

public class SearchFileByGlob extends SimpleFileVisitor<Path> {

    /**
     * Variable that is referring to PathMatcher.
     */
    private PathMatcher matcher;

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
     * @param pattern                               String that represents a glob pattern which will be used for files search
     * @param aLogFile                              log file path where to write a result
     * @param aWriteLog                             boolean key to check write of not write a log
     */
    public SearchFileByGlob(String pattern, Path aLogFile, boolean aWriteLog) {
        this.matcher = FileSystems.getDefault().getPathMatcher(String.format("glob:%s", pattern));
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

        if (name != null && this.matcher.matches(name)) {
            if (writeLog) {
                new WriteLog(logFile, file).write();
            } else {
                System.out.println(file);
            }
        }
    }

    /**
     * Method that overrides the method visitFile from SimpleFileVisitor class.
     * @param path                              a file to be checked against a pattern
     * @param attr                              file attributes
     * @return                                  FileVisitResult
     */
    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attr) {
        find(path, this.logFile, this.writeLog);
        return CONTINUE;
    }
}

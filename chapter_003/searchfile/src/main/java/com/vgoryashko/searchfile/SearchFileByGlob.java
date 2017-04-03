package com.vgoryashko.searchfile;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Class that implements searches a file in a file tree and writes a result in a file.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 03.04.2017
 */

public class SearchFileByGlob extends SimpleFileVisitor<Path> {

    private PathMatcher matcher;
    private Path logFile;
    private boolean writeLog;

    public SearchFileByGlob(String pattern, Path aLogFile, boolean aWriteLog) {
        this.matcher = FileSystems.getDefault().getPathMatcher(String.format("glob:%s", pattern));
        this.logFile = aLogFile;
        this.writeLog = aWriteLog;
    }

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

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attr) {
        find(path, this.logFile, this.writeLog);
        return CONTINUE;
    }
}

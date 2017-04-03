package com.vgoryashko.searchfile;

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

    private String fileName;
    private Path logFile;
    private boolean writeLog;

    public SearchFileByName(String aFileName, Path aLogFile, boolean aWriteLog) {
        this.fileName = aFileName;
        this.logFile = aLogFile;
        this.writeLog = aWriteLog;
    }

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
     * @param file
     * @param attrs
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        find(file, this.logFile, this.writeLog);
        return FileVisitResult.CONTINUE;
    }
}

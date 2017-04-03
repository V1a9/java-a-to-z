package com.vgoryashko.searchfile;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Class that implements
 *
 * @author Vlad Goryashko
 * @since 03.04.2017
 */

public class SearchFile extends SimpleFileVisitor<Path> {

    private PathMatcher matcher;

    public SearchFile(String pattern) {
        this.matcher = FileSystems.getDefault().getPathMatcher(String.format("glob:%s", pattern));
    }

    public void find(Path searchFile) {
        Path name = searchFile.getFileName();
        if (name != null && this.matcher.matches(name)) {
            System.out.println(searchFile);
        }
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attr) {
        find(path);
        return CONTINUE;
    }
}

package com.vgoryashko.multithreading.monitore.parallelsearch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Class that search a text in a all files with a given extension in a file system starting from a given root folder.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 9/14/17
 */
@ThreadSafe
public class ParallelSearch {

    /**
     * Variable that stores a path to a root folder.
     */
    private String root;

    /**
     * Variable that stores a text to be found.
     */
    private String text;

    /**
     * Variable that stores an extension of files where text to be found.
     */
    private String exts;

    /**
     * Variable that stores a list of files with a text.
     */
    @GuardedBy("this") private final List<String> result;

    /**
     * Constructor for the class.
     *
     * @param root folder to start search
     * @param exts extension of files where searching of a text to be performed
     * @param text to be found
     */
    public ParallelSearch(String root, String text, String exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
        this.result = new ArrayList<>();
    }

    /**
     * Class that is used for walking a file tree.
     *
     * @param <T> type of a parameter
     */
    private class WalkFileTree<T> extends SimpleFileVisitor<T> {

        /**
         * Invoked for a file in a directory.
         * <p>
         * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
         * CONTINUE}.
         *
         * @param path path to a root directory
         * @param attrs basic attributes
         * @return CONTINUE
         */
        @Override
        public FileVisitResult visitFile(T path, BasicFileAttributes attrs) throws IOException {

            Path currentPath = (Path) path;

            if ((currentPath.getFileName().toString().matches(String.format(".*%s", exts)))) {

                result.add(path.toString());

            }

            return FileVisitResult.CONTINUE;
        }
    }


    /**
     * Method that search files in a file tree with a given extension.
     */
    public class SearchFiles implements Runnable {

        /**
         * Instance variable of the class Thread.
         */
        private Thread searchFilesThread;

        /**
         * Constructor for the class.
         */
        SearchFiles() {

            this.searchFilesThread = new Thread(this, "Search files thread");

        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {

            SimpleFileVisitor<Path> walkFileTree = new WalkFileTree<>();

            try {

                Files.walkFileTree(Paths.get(root), walkFileTree);

            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }

    /**
     * Class that search a given text in a collection of files with a given extension.
     */
    public class SearchText implements Runnable {

        /**
         * Instance variable of the class Thread.
         */
        private Thread searchTextThread;

        /**
         * Constructor for the class.
         */
        SearchText() {

            this.searchTextThread = new Thread(this, "Search text thread");

        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {

            ListIterator<String> iterator = result.listIterator();

            while (iterator.hasNext()) {

                String file = iterator.next();

                String readString;

                boolean keep = false;

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                    while ((readString = reader.readLine()) != null) {

                        if (readString.matches(String.format("%s", text))) {

                            keep = true;

                        }

                    }

                } catch (IOException ioe) {

                    ioe.printStackTrace();

                }

                if (!keep) {
                    iterator.remove();
                }

            }

        }

    }

    /**
     * Method that perform main logic of the application.
     *
     * @return List<String> all files with a given text
     * @throws InterruptedException InterruptedException
     */
    public List<String> result() throws InterruptedException {

        SearchFiles searchFiles = new SearchFiles();
        SearchText searchText = new SearchText();

        searchFiles.searchFilesThread.start();
        searchFiles.searchFilesThread.join();

        searchText.searchTextThread.start();
        searchText.searchTextThread.join();

        return this.result;
    }


}

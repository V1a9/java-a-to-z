package com.vgoryashko.collectionslite.testperformance;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.TreeSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class that tests an implementation of collections performance application.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 5/2/17
 */
public class CheckPerformanceTest {

    /**
     * Constant that stores a file separator value.
     */
    private static final String FS = File.separator;

    /**
     * Constant that stores a path to a testFile.txt file.
     */
    private static final Path TESTFILE = Paths.get(String.format(".%sauxiliary%stestFile.txt", FS, FS));

    /**
     * Method that initializes test environments.
     *
     * @throws IOException <code>IOException</code>
     */
    @Before
    public void init() throws IOException {
        GenerateTextFile generateTextFile = new GenerateTextFile();
        if (!generateTextFile.check()) {
            generateTextFile.generate();
        } else if (Files.size(TESTFILE) == 0) {
            generateTextFile.generate();
        }
    }

    /**
     * Method that tests if testFile.txt is available and generates it prior a test if it is not available.
     *
     * @throws IOException <code>IOException</code>
     */
    @Test
    public void whenTestIsRunThenTestFileIsAvailable() throws IOException {
        assertTrue(Files.exists(TESTFILE));
    }

    /**
     * Method that tests filling of an ArrayList.
     */
    @Test
    public void whenAddMethodInvokedThenCollectionsFilled() {

        Collection<String> collection;
        long time;

        collection = new ArrayList<>();
        time = new CheckPerformance().add(collection, "line", 1000000);
        assertTrue(time > 0);
        System.out.printf("Process to fill %s with 1M string took: %s%s", "ArrayList", time, "ms");
        System.out.println();

        collection = new LinkedList<>();
        time = new CheckPerformance().add(collection, "line", 1000000);
        assertTrue(time > 0);
        System.out.printf("Process to fill %s with 1M string took: %s%s", "LinkedList", time, "ms");
        System.out.println();

        collection = new TreeSet<>();
        time = new CheckPerformance().add(collection, "line", 1000000);
        assertTrue(time > 0);
        System.out.printf("Process to fill %s with 1M string took: %s%s", "TreeSet", time, "ms");
        System.out.println();

    }

    /**
     * Method that tests removing of elements from a collection.
     */
    @Test
    public void whenRemoveMethodInvokedThenElementsRemoved() {

        CheckPerformance checkPerformance = new CheckPerformance();
        Collection<String> collection;
        long time;

        collection = new ArrayList<>();
        checkPerformance.add(collection, "line", 100000);
        time = new CheckPerformance().delete(collection, 25000);
        assertThat(collection.size(), is(75000));
        System.out.printf("Process to delete %d elements from %s took: %d%s", 2500, "ArrayList", time, "ms");
        System.out.println();

        collection = new LinkedList<>();
        checkPerformance.add(collection, "line", 500000);
        time = new CheckPerformance().delete(collection, 250000);
        assertThat(collection.size(), is(250000));
        System.out.printf("Process to delete %d elements from %s took: %d%s", 250000, "LinkedList", time, "ms");
        System.out.println();

        collection = new TreeSet<>();
        checkPerformance.add(collection, "line", 500000);
        time = new CheckPerformance().delete(collection, 250000);
        assertThat(collection.size(), is(250000));
        System.out.printf("Process to delete %d elements from %s took: %d%s", 250000, "TreeSet", time, "ms");
        System.out.println();
    }
}

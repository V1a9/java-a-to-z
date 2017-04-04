package com.vgoryashko.search;

import org.junit.Test;

import java.io.IOException;

/**
 * Class that test files' names to a file.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 4/3/17
 */
public class SearchTest {

    /**
     * Test search.
     */
    @Test
    public void searchTest() {

        String[] args = new String[]{"-d", "/home/v1a9/Projects/java-a-to-z/chapter_003/auxiliary",
                "-n", "*.txt", "-m", "-o", "log.txt"};
        Search search;
        try {
            search = new Search();
            search.main(args);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

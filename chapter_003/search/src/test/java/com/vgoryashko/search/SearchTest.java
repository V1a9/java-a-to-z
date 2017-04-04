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
     * Test root folder.
     */
    @Test
    public void rootTest() {

        String[] args = new String[]{"-d", "c:\\local_installation_source\\",
                "-n", "*.exe", "-m", "-o", "log.txt"};
        Search search;
        try {
            search = new Search();
            search.main(args);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

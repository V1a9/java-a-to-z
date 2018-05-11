package com.vgoryashko.multithreading.monitore.parallelsearch;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Class that tests application ParallelSearch.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 9/14/17
 */
public class ParallelSearchTest {

    /**
     * Constant that stores value of the folder separator.
     */
    private static final String FS = System.getProperty("file.separator");

    /**
     * Variable that stores a value for a root path.
     */
    private String rootPath;

    /**
     * Variable that stores a result of a text search.
     */
    private List<String> searchResult;

    /**
     * Method that setups test environments.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {

        this.rootPath = String.format(".%ssrc%stest%sresources%s", FS, FS, FS, FS);
        this.searchResult = new ArrayList<>();

    }


    /**
     * Method that tests ParallelSearch application.
     * @throws Exception Exception
     */
    @Test
    public void whenResultInvokedThenListOfFilesReturned() throws Exception {

        String[] expected = new String[]{
                String.format(".%ssrc%Stest%Sresources%Stest1%Stest1.txt", FS, FS, FS, FS, FS),
                String.format(".%ssrc%Stest%Sresources%Stest3%Stest3.txt", FS, FS, FS, FS, FS)
        };

        int index = 0;

        ParallelSearch parallelSearch = new ParallelSearch(this.rootPath, "test", "txt");
        this.searchResult = parallelSearch.result();

        for (String s : this.searchResult) {

            assertEquals(s, expected[index++]);

        }

    }

}
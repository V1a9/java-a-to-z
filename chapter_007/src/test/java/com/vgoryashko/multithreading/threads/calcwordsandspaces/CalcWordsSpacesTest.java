package com.vgoryashko.multithreading.threads.calcwordsandspaces;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class that tests CalcWordsSpaces application that calculates wards and spaces concurrently in a given file.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/5/17
 */
public class CalcWordsSpacesTest {

    /**
     * Constant that stores file separator char.
     */
    private static final String FS = System.getProperty("file.separator");

    /**
     * Variable that refers to a path of a file for processing.
     */
    private Path path;

    /**
     * Method that initializes test environments.
     */
    @Before
    public void init() {

        this.path = FileSystems.getDefault().getPath(String.format(".%ssrc%stest%sresources%stest.txt", FS, FS, FS, FS));

    }

    /**
     * Method that tests a result of the application execution.
     * @throws Exception Exception
     */
    @Test
    public void whenCalcWordsSpacesExecutedThenExpectedResultsAreAchieved() throws Exception {

        CalcWordsSpaces calcWordsSpaces = new CalcWordsSpaces(this.path);
        calcWordsSpaces.startApp();

        int[] result = calcWordsSpaces.getResult();

        assertThat(result[1], is(24));
        assertThat(result[0], is(23));

    }

}
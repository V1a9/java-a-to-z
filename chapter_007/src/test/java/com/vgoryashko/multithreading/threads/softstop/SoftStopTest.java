package com.vgoryashko.multithreading.threads.softstop;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class that tests an application that calculates chars in a file and auto terminates after a given time set by a user.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 9/11/17
 */
public class SoftStopTest {

    /**
     * Constant that stores file separator char.
     */
    private static final String FS = System.getProperty("file.separator");

    /**
     * Variable that refers to an instance of CalcWordsSpaces.
     */
    private SoftStop softStop;

    /**
     * Variable that refers to a path of a short file for processing.
     */
    private Path pathShort;

    /**
     * Method that initializes test environments.
     */
    @Before
    public void setUp() {

        this.pathShort = FileSystems.getDefault().getPath(String.format(".%ssrc%stest%sresources%stest.txt", FS, FS, FS, FS));
        softStop = new SoftStop(this.pathShort, 1000);

    }

    /**
     * Method that tests calculate().
     *
     * @throws Exception Exception
     */
    @Test
    public void whenCalculateInvokedWithEnoughTimeSetThenResultReceived() throws Exception {

        assertThat(softStop.calculate(), is(126L));

    }

}
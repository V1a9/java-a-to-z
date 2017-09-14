package com.vgoryashko.multithreading.monitore.sync;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Class that tests Multithreading counter.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/13/17
 */
public class SynchronizedTest {

    /**
     * Method that tests Synchronized class.
     * @throws Exception Exception
     */
    @Test
    public void whenStartAppInvokedThenTwoThreadsIncrementsCounterByFour() throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setOut(new PrintStream(out));
        Synchronized aSynchronized = new Synchronized();
        aSynchronized.startApp();
        assertEquals(out.toString(), "4");

    }

}
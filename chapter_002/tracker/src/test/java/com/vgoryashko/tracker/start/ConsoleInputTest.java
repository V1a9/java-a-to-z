package com.vgoryashko.tracker.start;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import java.io.*;
import java.lang.String;

/**
 * Created by Vlad Goryashko on 11/21/2016.
 */
public class ConsoleInputTest {

    /**
     * Method for testing of the method ask() in the ConsoleInput class.
     */
   @Ignore @Test
    public void askTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ConsoleInput consoleInput = new ConsoleInput();
        consoleInput.ask("Some question: ");
        assertThat(out.toString(), is(""));
    }
}

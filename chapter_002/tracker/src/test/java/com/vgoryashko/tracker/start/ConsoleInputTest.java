package com.vgoryashko.tracker.start;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Class class that implements user action of adding a new Bug into the system.
 * @author Vlad Goryashko
 * @version 0.2
 * @since 19/12/2016
 */
public class ConsoleInputTest {

    /**
     * Method for testing of the method ask() in the ConsoleInput class.
     */
   @Test
    public void askTest() {
        String buf = "2,148,289";
        InputStream in = new ByteArrayInputStream(buf.getBytes());
        System.setIn(in);
        Input input = new ConsoleInput();
        assertThat(input.ask("Please enter number: "), is("2,148,289"));
    }
}

package com.vgoryashko.tracker.start;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class for testing StartUI class.
 * @author Vlad Goryashko
 * @version 1.0
 * @since 11.21.2016
 */
public class StartUITest {

    /**
     * Method for testing StartUI.init() method.
     */
    @Test
    public void initTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        StartUI startUI = new StartUI();
        startUI.init();
        assertThat(out.toString(), is("Task_1\n"));
    }
}

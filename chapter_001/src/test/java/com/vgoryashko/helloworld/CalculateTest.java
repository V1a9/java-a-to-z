package com.vgoryashko.helloworld;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Vlad Goryashko on 11/19/16.
 */
public class CalculateTest {

    /**
     * Method that test Calculate class.
     */
    @Test
    public void whenCalculateStartedHelloWorldIsPrintedInTheConsole() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Calculate().main(new String[]{null});
        assertThat(out.toString(), is("Hello world."));
    }
}

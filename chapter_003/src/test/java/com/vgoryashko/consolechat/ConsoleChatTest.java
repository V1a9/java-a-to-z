package com.vgoryashko.consolechat;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class that performs sorting of a source file and writes a result into a new file.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 07.02.2017
 */
public class ConsoleChatTest {

    /**
     * Variable that referring to an object of ConsoleInput class.
     */
    private In in;

    /**
     * Variable that referring to an object of ConsoleChat.
     */
    private ConsoleChat consoleChat;

    /**
     * Method that setups test environments.
     */
    @Before
    public void initSetUp() {
        consoleChat = new ConsoleChat();
        in = new In();
    }

    /**
     * Method that tests ConsoleInput class.
     */
    @Test
    public void whenMethodInIsInvokedThenStringFromConsoleReturned() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("Hello".getBytes());
        System.setIn(inputStream);
        assertThat(in.in(), is("Hello"));
    }
}

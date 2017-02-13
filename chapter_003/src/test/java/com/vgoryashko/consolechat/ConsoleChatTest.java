package com.vgoryashko.consolechat;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class that performs sorting of a source file and writes a result into a new file.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 13.02.2017
 */
public class ConsoleChatTest {

    /**
     * Variable that referring to an object of ConsoleChat.
     */
    private ConsoleChat consoleChat;

    /**
     * Variable that referring to an object of SplitFileToArray.
     */
    private SplitFileToArray splitFileToArray;

    /**
     * Method that setups test environments.
     */
    @Before
    public void initSetUp() {
        consoleChat = new ConsoleChat();
        splitFileToArray = new SplitFileToArray();
    }

    /**
     * Method that tests SplitFile class.
     */
    @Test
    public void whenSplitFileMethodInvokedThenArrayOfStringsReturned() {
        File answers = new File(String.format(".%sanswers.txt", File.separator));
        String[] answersCheck = splitFileToArray.splitFile(answers);
        assertThat(answersCheck, is(new String[]{"Hey!", "How are you?", "Not bad, thanks!", "You too.",
                                                    "Everything is great!!!", "Good luck!", "Take care!", "Keep in touch."}));

    }
}

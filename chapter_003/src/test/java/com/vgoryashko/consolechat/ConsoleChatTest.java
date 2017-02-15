package com.vgoryashko.consolechat;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.io.IOException;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class that performs sorting of a source file and writes a result into a new file.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 15.02.2017
 */
public class ConsoleChatTest {

    /**
     * Variable that stores path to a log.txt.
     */
    private File logFile;

    /**
     * Variable that referring to an object of SplitFileToArray.
     */
    private SplitFileToArray splitFileToArray;

    /**
     * Method that setups test environments.
     */
    @Before
    public void initSetUp() {
        logFile = new File(String.format(".%slog.txt", File.separator));
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

    /**
     * Method that tests chat() method.
     */
    @Test
    public void whenChatMethodInvokedThen() {
        File inputMessages = new File(String.format(".%sinputMessages.txt", File.separator));
        SplitFileToArray splitFileToArray = new SplitFileToArray();
        String[] log = null;
        String[] expectedResult = new String[]{"hello", "answer", "how are you?", "answer", "pause", "hello",
                "resume", "answer", "hello", "answer", "exit"};
        ConsoleChat consoleChat;
        if (logFile.exists()) {
            logFile.delete();
        }
        try (FileInputStream inputStream = new FileInputStream(inputMessages);
             RandomAccessFile logReader = new RandomAccessFile(logFile, "rw")) {
            System.setIn(inputStream);
            consoleChat = new ConsoleChat(System.in, new File(String.format(".%sanswers_test.txt", File.separator)));
            consoleChat.chat();
            log = splitFileToArray.splitFile(new File(String.format(".%slog.txt", File.separator)));
            for (int index = 0; index < expectedResult.length; index++) {
                assertTrue(log[index].equals(expectedResult[index]));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

package com.vgoryashko.removeabuse;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Class that test class that removes abuse words from an input stream.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 12.01.2017
 */
public class RemoveAbuseTest {

    /**
     * Variable that holds a text that is going to be tested against abuse words.
     */
    private String testText;

    /**
     * Array of abuse words.
     */
    private String[] testAbuseWords;
    /**
     * Variable for RemoveAbuse class.
     */
    private RemoveAbuse removeAbuse;
    /**
     * Variable for ByteArrayInputStream class.
     */
    private ByteArrayInputStream inputStream;

    /**
     * Method that initializing test environments before each test case.
     */
    @Before
    public void initSetUp() {
        testText = "AAA bbb ccc fff eee ggg hhh.";
        testAbuseWords = new String[]{"ccc", "eee"};
        removeAbuse = new RemoveAbuse();
        inputStream = new ByteArrayInputStream(testText.getBytes());
    }

    /**
     * Method that tests removing of abuse words from input stream and sent a result into output stream.
     */
    @Test
    public void whenAbuseWordsSentTheyFiltered() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        removeAbuse.dropAbuses(inputStream, System.out, testAbuseWords);
        assertThat(out.toString(), is("AAA bbb fff ggg hhh."));
    }
}

package com.vgoryashko.removeabuse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Class that test class that removes abuse words from an input stream.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 12.01.2017
 */
public class RemoveAbuseTest {

    private String testText;
    private String[] testAbuseWords;
    private RemoveAbuse removeAbuse;
    private ByteArrayInputStream inputStream;

    @Before
    public void initSetUp() {
        testText = "AAA bbb ccc fff eee ggg hhh.";
        testAbuseWords = new String[]{"ccc", "eee"};
        removeAbuse = new RemoveAbuse();
        inputStream = new ByteArrayInputStream(testText.getBytes());
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

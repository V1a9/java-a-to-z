package com.vgoryashko.removeabuse;

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

/**
 * Class that test class that removes abuse words from an input stream.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 11.01.2017
 */
public class RemoveAbuseTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenAbuseWordsSentTheyFiltered() {
        String testText = "AAAA bbb cccc xxxx dddd cccc sssss";
        String[] testAbuseWords = new String[]{"AAAA", "cccc"};
        RemoveAbuse removeAbuse = new RemoveAbuse();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testText.getBytes());
        removeAbuse.dropAbuses(inputStream, System.out, testAbuseWords);
    }
}

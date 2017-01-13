package com.vgoryashko.checkbytestream;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Class that tests class that performs checking of input stream of bytes for even integer.
 *
 * @author Vlad Goryashko
 * @version 2.0
 * @since 11.01.2017
 */

public class CheckByteStreamForEvenNumberTest {

    /**
     * Instance variable of CheckByteStreamForEvenNumber class.
     */
    private CheckByteStreamForEvenNumber check;

    /**
     * Rule for testing exceptions.
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Method that initializing test environments before each test case.
     */
    @Before
    public void initSetUp() {
        check = new CheckByteStreamForEvenNumber();
    }

    /**
     * Method that tests a case when an even number is sent.
     */
    @Test
    public void whenEvenNumberSentResultIsTrue() {
        ByteArrayInputStream stream = new ByteArrayInputStream("20".getBytes());
        assertTrue(check.isNumber(stream));
    }

    /**
     * Method that tests a case when an odd number is sent.
     */
    @Test
    public void whenOddNumberSentResultIsFalse() {
        ByteArrayInputStream stream = new ByteArrayInputStream("11".getBytes());
        assertFalse(check.isNumber(stream));
    }

    /**
     * Method that tests a case when next element available after first byte series.
     */
    @Test
    public void whenNextElementAvailableResultIsFalse() {
        ByteArrayInputStream stream = new ByteArrayInputStream("20 1".getBytes());
        assertFalse(check.isNumber(stream));
    }

    /**
     * Method that tests a case when wrong element is sent (exception is expected).
     *
     * @throws NoSuchElementException                           in case there is no elements available for reading.
     */
    @Test
    public void whenWrongElementSentResultIsException() throws NoSuchElementException {
        expectedException.expect(NoSuchElementException.class);
        ByteArrayInputStream stream = new ByteArrayInputStream("20,1".getBytes());
        check.isNumber(stream);
        throw new NoSuchElementException();
    }
}

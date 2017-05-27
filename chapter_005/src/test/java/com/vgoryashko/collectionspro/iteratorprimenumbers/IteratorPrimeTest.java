package com.vgoryashko.collectionspro.iteratorprimenumbers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class that tests implementation of the iterator that returns prime numbers.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 27.05.2017
 */
public class IteratorPrimeTest {

    /**
     * Method that tests IteratorEvenNumbers.
     */
    @Test
    public void whenIteratorPrimeInvokedThenAllPrimeNumbersReturned() {

        IteratorPrime iteratorPrime = new IteratorPrime(new ArrayList<>(Arrays.asList(7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 97)));

        int[] expected = new int[]{7, 11, 13, 17, 97};
        int index = 0;
        while (iteratorPrime.hasNext()) {
            assertThat(expected[index++], is(iteratorPrime.next()));

        }
    }
}
package com.vgoryashko.collectionspro.iteratorevennumbers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class that tests IteratorEvenNumbers.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 21.05.2017
 */
public class IteratorEvenTest {

    /**
     * Method that tests IteratorEvenNumbers.
     */
    @Test
    public void whenIteratorEvenNumbersInvokedThenAllEvenNumbersReturned() {

        IteratorEven iteratorEven = new IteratorEven(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));

        int[] expected = new int[]{2, 4, 6, 8};

        while (iteratorEven.hasNext()) {
            for (int element : expected) {
                assertThat(element, is(iteratorEven.next()));
            }
        }
    }
}
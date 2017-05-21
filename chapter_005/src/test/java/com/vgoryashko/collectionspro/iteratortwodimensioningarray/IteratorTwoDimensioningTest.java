package com.vgoryashko.collectionspro.iteratortwodimensioningarray;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Class that tests an iterator for a two-dimensioning arrays.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 21.05.2017
 */
public class IteratorTwoDimensioningTest {
    @Test
    public void hasNext() throws Exception {

        IteratorTwoDimensioning it = new IteratorTwoDimensioning(new int[][] {{1, 2}, {3, 4}});

        int[][] expected = new int[][] {{1, 2}, {3, 4}};

        while (it.hasNext()) {
            for (int[] array : expected) {
                for (int integer : array) {
                    assertThat(it.next(), is(integer));
                }
            }
        }
    }
}
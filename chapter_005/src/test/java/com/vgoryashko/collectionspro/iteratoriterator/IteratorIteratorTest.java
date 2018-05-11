package com.vgoryashko.collectionspro.iteratoriterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class that tests Iterator of Iterators.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 23.05.2017
 */
public class IteratorIteratorTest {

    /**
     * Method that tests of Iterator<Iterator<Integer>> to Iterator<Integer>.
     */
    @Test
    public void whenIteratorIteratorInvokedThenExpectedResultAchieved() {

        List<Iterator<Integer>> iterators = new ArrayList<>();

        List<Integer> list1 = new ArrayList<>(Arrays.asList(4, 2, 0, 4, 6, 4, 9));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(0, 9, 8, 7, 5));
        List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 7, 0, 9, 8, 4));
        List<Integer> expected = new ArrayList<>(Arrays.asList(4, 2, 0, 4, 6, 4, 9, 0, 9, 8, 7, 5, 1, 3, 5, 6, 7, 0, 9, 8, 4));

        iterators.add(list1.iterator());
        iterators.add(list2.iterator());
        iterators.add(list3.iterator());

        IteratorIterator iteratorIterator = new IteratorIterator(iterators.iterator());
        Iterator<Integer> expectedIterator = expected.iterator();

        while (iteratorIterator.hasNext()) {

                assertThat(iteratorIterator.next(), is(expectedIterator.next()));
        }
    }
}
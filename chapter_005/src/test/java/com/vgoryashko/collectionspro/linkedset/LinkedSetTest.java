package com.vgoryashko.collectionspro.linkedset;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class that test LinkedSet.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 6/12/17
 */
public class LinkedSetTest {

    private LinkedSet<Integer> linkedSet;
    private Iterator<Integer> iterator;
    private int[] expected;
    private int index;

    @Before
    public void init() {

        linkedSet = new LinkedSet<>();
        iterator = linkedSet.iterator();
        index = 0;

    }

    /**
     * Method that tests Set collection.
     *
     * @throws Exception Exception.
     */
    @Test
    public void whenAddIsInvokedThenOnlyUniqueElementsAreAdded() throws Exception {

        expected = new int[]{1, 2, 3, 5, 8, 9};

        linkedSet.add(1);
        linkedSet.add(2);
        linkedSet.add(3);
        linkedSet.add(8);
        linkedSet.add(1);
        linkedSet.add(9);
        linkedSet.add(5);

        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(expected[index++]));
        }

    }

    @Test
    public void test() {

        expected = new int[]{1, 2, 3, 4, 5, 6, 7};

        linkedSet.add(1);
        linkedSet.add(2);
        linkedSet.add(3);
        linkedSet.add(2);
        linkedSet.add(5);
        linkedSet.add(6);
        linkedSet.add(7);
        linkedSet.add(4);

        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(expected[index++]));
        }


    }

}
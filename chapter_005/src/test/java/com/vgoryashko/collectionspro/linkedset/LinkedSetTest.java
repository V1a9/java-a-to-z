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
 * @version 0.6
 * @since 6/13/17
 */
public class LinkedSetTest {

    /**
     * Variable that referring to linked list instance.
     */
    private LinkedSet<Integer> linkedSet;

    /**
     * Variable that is referring to an instance if iterator().
     */
    private Iterator<Integer> iterator;

    /**
     * Array that stores expected result.
     */
    private int[] expected;

    /**
     * Index that is used to move in the expected result.
     */
    private int index;

    /**
     * Method that initialize test environments.
     */
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
    public void whenAddIsInvokedOddTimesThenOnlyUniqueElementsAreAdded() throws Exception {

        expected = new int[]{1, 2, 3, 5, 8, 9};

        linkedSet.add(1);
        linkedSet.add(2);
        linkedSet.add(3);
        linkedSet.add(8);
        linkedSet.add(9);
        linkedSet.add(5);

        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(expected[index++]));
        }
    }

    /**
     * Method that tests add() with binary search.
     */
    @Test
    public void whenAddIsInvokedEvenTimesThenOnlyUniqueElementsAreAdded() {

        expected = new int[]{1, 2, 3, 4, 5, 6, 7};

        linkedSet.add(1);
        linkedSet.add(2);
        linkedSet.add(1);
        linkedSet.add(2);
        linkedSet.add(3);
        linkedSet.add(5);
        linkedSet.add(6);
        linkedSet.add(7);
        linkedSet.add(4);

        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(expected[index++]));
        }
    }
}
package com.vgoryashko.collectionspro.linkedset;

import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Class that test LinkedSet.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 6/6/17
 */
public class LinkedSetTest {

    /**
     * Method that tests Set collection.
     *
     * @throws Exception Exception.
     */
    @Test
    public void whenAddIsInvokedThenOnlyUniqueElementsAreAdded() throws Exception {

        LinkedSet<Integer> linkedSet = new LinkedSet<>();


        int[] expected = new int[]{1, 2, 3};
        int index = 0;

        linkedSet.add(1);
        linkedSet.add(2);
        linkedSet.add(3);
        linkedSet.add(8);
        linkedSet.add(9);
        linkedSet.add(5);

        Iterator<Integer> iterator = linkedSet.iterator();

        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(expected[index++]));
        }

        assertEquals(index, 3);

    }

}
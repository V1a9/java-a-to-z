package com.vgoryashko.collectionspro.set;

import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class that test implementation of Set collection.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 6/5/17
 */
public class SetTest {

    /**
     * Method that test Set collection.
     *
     * @throws Exception Exception
     */
    @Test
    public void whenSetCreatedAndNewElementsAddedItDoesntHaveDuplicates() throws Exception {

        int[] expected = new int[]{1, 2, 3};

        Set<Integer> set = new Set<>();

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(3);

        Iterator<Integer> iterator = set.iterator();
        int index = 0;

        while (iterator.hasNext()) {

            assertThat(iterator.next(), is(expected[index++]));

        }

    }

}
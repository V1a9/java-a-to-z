package com.vgoryashko.collectionspro.set;

import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class that test implementation of Set collection.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 6/10/17
 */
public class SetTest {

    /**
     * Method that test Set collection.
     *
     * @throws Exception Exception
     */
    @Test
    public void whenSetCreatedAndNewElementsAddedItDoesntHaveDuplicates() throws Exception {

        String[] expected = new String[]{"Hi", "Yes", "No"};

        Set<String> set = new Set<>();

        set.add("Hi");
        set.add("Yes");
        set.add("No");
        set.add("No");

        Iterator<String> iterator = set.iterator();

        int index = 0;

        while (iterator.hasNext()) {

            assertThat(iterator.next(), is(expected[index++]));

        }

    }

}
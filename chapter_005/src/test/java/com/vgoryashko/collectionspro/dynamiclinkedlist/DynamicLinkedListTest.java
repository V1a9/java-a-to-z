package com.vgoryashko.collectionspro.dynamiclinkedlist;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class that tests Dynamic Linked List.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 5/31/17
 */
public class DynamicLinkedListTest {

    /**
     * Variable that referring to collection.
     */
    private DynamicLinkedList<Integer> linkedList;

    /**
     * Variable that referring to Iterator.
     */
    private Iterator<Integer> iterator;

    /**
     * Method that initialising test environments.
     */
    @Before
    public void init() {

        linkedList = new DynamicLinkedList<>();

        iterator = linkedList.iterator();

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

    }

    /**
     * Method that tests adding of elements into collection.
     * @throws Exception Exception
     */
    @Test
    public void whenElementsAddedThenTheyInsertedIntoCollection() throws Exception {



        int[] expected = new int[]{1, 2, 3};

        int i = 0;

        while (iterator.hasNext()) {
            assertThat(iterator.next(), is(expected[i++]));
        }
    }

    /**
     * Method that tests get() method.
     * @throws Exception Exception
     */
    @Test
    public void whenElementRequestedByIndexItReturned() throws Exception {

        assertThat(linkedList.get(1), is(2));

    }

}
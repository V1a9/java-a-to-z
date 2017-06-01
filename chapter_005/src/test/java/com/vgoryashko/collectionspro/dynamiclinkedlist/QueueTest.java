package com.vgoryashko.collectionspro.dynamiclinkedlist;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class that tests Queue implementation.
 *
 * @author Vlad Goryashko
 * @since 01.06.2017
 * @version 0.1
 */

public class QueueTest {

    /**
     * Variable that referring to collection.
     */
    private DynamicLinkedList<Integer> queue;

    /**
     * Method that initialising test environments.
     */
    @Before
    public void init() {

        queue = new DynamicLinkedList<>();

        queue.add(1);
        queue.add(2);
        queue.add(3);

    }


    /**
     * Method that tests element() method.
     *
     * @throws Exception NoSuchElementException
     */
    @Test
    public void whenElementInvokedThenItReturnsHeadOfQueue() throws Exception {

        assertThat(queue.element(), is(1));

    }

    /**
     * Method that tests remove() method.
     *
     * @throws Exception NoSuchElementException
     */
    @Test
    public void remove() throws Exception {

        assertThat(queue.remove(), is(1));
        assertThat(queue.element(), is(2));

    }

}
package com.vgoryashko.collectionspro.dynamiclinkedlist;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Class that tests stack implementation.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 5/31/17
 */
public class StackTest {

    /**
     * Variable that referring to collection.
     */
    private DynamicLinkedList<Integer> stack;

    /**
     * Variable that referring to Iterator.
     */
    private Iterator<Integer> iterator;

    /**
     * Method that initialising test environments.
     */
    @Before
    public void init() {

        stack = new DynamicLinkedList<>();

        iterator = stack.iterator();

        stack.push(1);
        stack.push(2);
        stack.push(3);

    }

    /**
     * Method that test push method.
     *
     * @throws Exception EmptyStackException
     */
    @Test
    public void whenElementPushedThenItPutOntoTopOfStack() throws Exception {

        assertThat(stack.peek(), is(3));

    }

    /**
     * Methood that tests if stack is empty.
     *
     * @throws Exception EmptyStackException
     */
    @Test
    public void whenEmptyMethodInvokedThenItShowsIfStackIsEmpty() throws Exception {

        assertTrue(stack.empty());

    }

    /**
     * Method tests removing of element at the top of stack.
     * @throws Exception EmptyStackException
     */
    @Test
    public void whenPopInvokedThenLastElementRemovedAndReturned() throws Exception {

        assertThat(stack.pop(), is(3));
        assertThat(stack.peek(), is(2));

    }

}
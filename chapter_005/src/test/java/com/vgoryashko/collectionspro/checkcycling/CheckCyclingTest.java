package com.vgoryashko.collectionspro.checkcycling;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Class that tests CheckCycling class.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 6/5/17
 *
 */
public class CheckCyclingTest {

    /**
     * Variables that referring to nodes of the list.
     */
    private Node<Integer> node1, node2, node3, node4;

    /**
     * Variable that referring to an instance of CheckCycling class.
     */
    private CheckCycling<Integer> cycling;

    /**
     * Method that setups test environments.
     */
    @Before
    public void init() {

        cycling = new CheckCycling<>();

        node1 = new Node<>(1, null);
        node2 = new Node<>(2, null);
        node3 = new Node<>(3, null);
        node4 = new Node<>(4, null);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
    }

    /**
     * Method that proves that cycling exists.
     */
    @Test
    public void whenCyclingExistsThenItsDetected() {

        node4.setNext(node3);

        assertThat(cycling.hasCycling(node1), is(true));

    }

    /**
     * Method that there is no cycling in the given list.
     */
    @Test
    public void whenCyclingNotExistsThenItsNotDetected() {

        node4.setNext(null);
        assertFalse(cycling.hasCycling(node1));

    }

}
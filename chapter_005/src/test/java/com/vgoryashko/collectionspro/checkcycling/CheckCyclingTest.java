package com.vgoryashko.collectionspro.checkcycling;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class that tests CheckCycling class.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 6/1/17
 */
public class CheckCyclingTest<T> {


    @Test
    public void whenHasCyclingInvokedThenItChecksListForCycling() {

        Node<Integer> node1 = new Node<>(1, null);
        Node<Integer> node2 = new Node<>(2, null);
        Node<Integer> node3 = new Node<>(3, null);
        Node<Integer> node4 = new Node<>(4, null);
        Node<Integer> node5 = new Node<>(5, null);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node1);

        CheckCycling<Integer> cycling = new CheckCycling<>();

        assertThat(cycling.hasCycling(node1), is(true));
//        assertThat(cycling.hasCycling(node1), is(false));

    }

}
package com.vgoryashko.collectionspro.treenodesswap;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class that tests DFS swap of nodes in a tree.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 08.08.2017
 */

public class DepthBreadthFirstSearchSwapTest {

    /**
     * Variables that are referring to nodes of a tree.
     */
    private Node<Integer> n0, n1, n2;

    /**
     * Method that initializes test environments.
     */
    @Before
    public void init() {

        n1 = new Node<>(
                new Node<>(null, null, 3),
                new Node<>(null, null, 4), 1);

        n2 = new Node<>(null,
                new Node<>(null, null, 5), 2);

        n0 = new Node<>(n1, n2, 0);

    }

    /**
     * Method that tests Depth-First search swap method.
     */
    @Test
    public void whenDFSSInvokedThenTreeSwapped() {

        new DepthFirstSearchSwap(n0).depthFirstSearchSwap();

        assertThat(n0.getLeft(), is(n2));
        assertThat(n0.getRight(), is(n1));
        assertThat(n1.getRight().getValue(), is(3));
        assertThat(n1.getLeft().getValue(), is(4));

    }

    /**
     * Method that tests Breadth-First swap method.
     */
    @Test
    public void whenBFSSInvokedThenTreeSwapped() {

        new BreadthFirstSearchSwap(n0).breadthFirstSearchSwap();

        assertThat(n0.getLeft(), is(n2));
        assertThat(n0.getRight(), is(n1));
        assertThat(n1.getRight().getValue(), is(3));
        assertThat(n1.getLeft().getValue(), is(4));

    }

}
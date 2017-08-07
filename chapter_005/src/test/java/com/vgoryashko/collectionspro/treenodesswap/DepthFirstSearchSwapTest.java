package com.vgoryashko.collectionspro.treenodesswap;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class that tests DFS swap of nodes in a tree.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 07.08.2017
 */

public class DepthFirstSearchSwapTest {

    /**
     * Method that tests Depth-First search swap method.
     */
    @Test
    public void whenDFSSInvokedThenTreeSwapped() {

        Node<Integer> n0;

        Node<Integer> n1 = new Node<>(
                new Node<>(null, null, 3),
                new Node<>(null, null, 4), 1);

        Node<Integer> n2 = new Node<>(null,
                new Node<>(null, null, 5), 2);

        n0 = new Node<>(n1, n2, 0);

        new DepthFirstSearchSwap(n0).depthFirstSearchSwap();

        assertThat(n0.getLeft(), is(n2));
        assertThat(n0.getRight(), is(n1));
        assertThat(n1.getRight().getValue(), is(3));
        assertThat(n1.getLeft().getValue(), is(4));

    }

}
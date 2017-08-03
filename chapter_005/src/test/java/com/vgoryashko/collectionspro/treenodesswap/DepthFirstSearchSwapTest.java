package com.vgoryashko.collectionspro.treenodesswap;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class that tests
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 03.08.2017
 */

public class DepthFirstSearchSwapTest {

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

        System.out.println("Pause");

    }

}
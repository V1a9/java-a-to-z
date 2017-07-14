package com.vgoryashko.collectionspro.tree;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class that
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 7/14/17
 */
public class TreeTest {

    /**
     * Variable that referring to an instance of Simple Tree.
     */
    private Tree<Integer> simpleTree;

    /**
     * Method that initializes test environments.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        simpleTree = new Tree<>();

    }

    @Test
    public void whenAddInvokedThenElementAdded() throws Exception {

        simpleTree.add(null, 1);
        simpleTree.add(1, 2);
        simpleTree.add(2, 3);
        simpleTree.add(1, 4);

    }

}
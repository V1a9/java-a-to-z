package com.vgoryashko.collectionspro.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class that tests Simple Tree class.
 *
 * @author Vlad Goryashko
 * @version 0.8
 * @since 7/18/17
 */
public class TreeTest {

    /**
     * Variable that referring to an instance of Simple Tree.
     */
    private Tree<Integer> simpleTree;

    /**
     * Method that initializes test environments.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {

        simpleTree = new Tree<>();

        simpleTree.add(8);
        simpleTree.add(10);
        simpleTree.add(3);
        simpleTree.add(1);
        simpleTree.add(4);
        simpleTree.add(14);
        simpleTree.add(13);
        simpleTree.add(15);

    }

    /**
     * Method that tests Simple Tree class.
     * @throws Exception Exception
     */
    @Test
    public void whenAddInvokedThenElementAdded() throws Exception {


        int[] expected = new int[]{8, 3, 1, 4, 10, 14, 13, 15};
        int index = 0;

        Iterator<Integer> iterator = simpleTree.iterator();

        while (iterator.hasNext()) {

            assertThat(iterator.next(), is(expected[index++]));

        }

    }

    /**
     * Method that tests whether a tree is a binary tree.
     */
    @Test
    public void whenIsBinaryInvokedThenItReturnsTrue() {

        assertTrue(simpleTree.isBinary());

    }
}
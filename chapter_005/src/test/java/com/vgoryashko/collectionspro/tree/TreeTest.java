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
 * @version 0.6
 * @since 7/17/17
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

    }

    /**
     * Method that tests Simple Tree class.
     * @throws Exception Exception
     */
    @Test
    public void whenAddInvokedThenElementAdded() throws Exception {

        int[] expected = new int[]{1, 2, 3, 5, 4, 6, 7, 8};
        int index = 0;

        simpleTree.add(null, 1);
        simpleTree.add(1, 2);
        simpleTree.add(2, 3);
        simpleTree.add(1, 4);
        simpleTree.add(3, 5);
        simpleTree.add(4, 6);
        simpleTree.add(1, 7);
        simpleTree.add(7, 8);

        Iterator<Integer> iterator = simpleTree.iterator();

        while (iterator.hasNext()) {

            assertThat(iterator.next(), is(expected[index++]));

        }

    }

    @Test
    public void testRecursion() {

        simpleTree.add(null, 1);
        simpleTree.add(1, 2);
        simpleTree.add(2, 3);
        simpleTree.add(1, 4);
        simpleTree.add(3, 5);
        simpleTree.add(4, 6);
        simpleTree.add(2, 7);
        simpleTree.add(6, 8);

        assertTrue(simpleTree.isBinary());

    }

}
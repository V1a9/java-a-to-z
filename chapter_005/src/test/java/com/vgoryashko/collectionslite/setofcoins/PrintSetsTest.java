package com.vgoryashko.collectionslite.setofcoins;

import org.junit.Test;

/**
 * Class that tests implementation of classes that print out all possible sets of coins.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 5/5/17
 */
public class PrintSetsTest {

    /**
     * Test
     */
    @Test
    public void printSetsTest() {

        new PrintSets().printSets(new int[]{1, 5, 10}, 30);

    }
}

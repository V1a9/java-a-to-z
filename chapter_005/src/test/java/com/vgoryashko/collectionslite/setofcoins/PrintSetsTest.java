package com.vgoryashko.collectionslite.setofcoins;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that tests implementation of classes that print out all possible sets of coins.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 5/5/17
 */
public class PrintSetsTest {

    /**
     * Test
     */
    @Test
    public void printSetsTest() {

        List<List<Integer>> result = new ArrayList<>(new PrintSets().printSets(new int[]{1, 5, 10}, 40));
        for (List<Integer> list : result) {
            for (Integer integer : list) {
                System.out.print(integer);
            }
            System.out.println();
        }
    }
}

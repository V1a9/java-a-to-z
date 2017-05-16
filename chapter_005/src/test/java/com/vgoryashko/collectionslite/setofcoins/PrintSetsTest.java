package com.vgoryashko.collectionslite.setofcoins;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * Class that tests implementation of classes that print out all possible sets of coins.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 5/16/17
 */
public class PrintSetsTest {

    /**
     * Test of the printSets() method.
     */
    @Test
    public void printSetsTest() {

        List<List<Integer>> result = new ArrayList<>(new PrintSets().printSets(new int[]{1, 5}, 15));
        List<List<Integer>> expectedResult = new ArrayList<>();

        expectedResult.add(new ArrayList<>(Arrays.asList(5, 5, 5)));
        expectedResult.add(new ArrayList<>(Arrays.asList(5, 5, 1, 1, 1, 1, 1)));
        expectedResult.add(new ArrayList<>(Arrays.asList(5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)));
        expectedResult.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)));

        for (int i = 0; i < result.size(); i++) {
            assertEquals(result.get(i), expectedResult.get(i));
        }
    }
}

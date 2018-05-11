package com.vgoryashko.collectionslite.convertlist;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that tests ConvertList.class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/23/17
 */
public class ConvertListTest {

    /**
     * Variable that stores reference to an instance of the ConvertList class.
     */
    private ConvertList convertList;

    /**
     * Method that setups testing environments.
     */
    @Before
    public void init() {
        convertList = new ConvertList();
    }

    /**
     * Test which checks toList() method.
     */
    @Test
    public void whenToListInvokedThenArrayConvertedToList() {

        int[][] array = new int[][]{{0, 1, 2, 3}, {4, 5, 6, 7}};

        List<Integer> listExpected = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            listExpected.add(i);
        }
        List<Integer> list = convertList.toList(array);
        assertTrue(listExpected.equals(list));
    }

    /**
     * Test which checks toArray() method.
     */
    @Test
    public void whenToArrayInvokedThenListConvertedToArray() {

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            list.add(i);
        }

        int[][] arrayExpected = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 0}};
        int[][] arrayResult = convertList.toArray(list, 3);

        for (int i = 0; i < 3; i++) {
            assertTrue(Arrays.equals(arrayExpected[i], arrayResult[i]));
        }
    }
}

package com.vgoryashko.collectionslite.convertarraylist;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that tests implementation of the class that converts List of arrays to the List<Integer>.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/23/17
 */
public class ConvertArrayListTest {

    /**
     * Method that tests implementation of the class that converts List of arrays to the List<Integer>.
     */
    @Test
    public void whenConvertInvokedThenListOfArraysConvertedToListOfInteger() {

        ConvertArrayList convertArrayList = new ConvertArrayList();

        List<Integer> listExpected = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            listExpected.add(i);
        }

        Object[] objects = new Object[3];
        objects[0] = new int[]{0, 1};
        objects[1] = new int[]{2, 3};
        objects[2] = new int[]{4, 5, 6};

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            list.add((int[]) objects[i]);
        }

        assertTrue(convertArrayList.convert(list).equals(listExpected));

    }
}

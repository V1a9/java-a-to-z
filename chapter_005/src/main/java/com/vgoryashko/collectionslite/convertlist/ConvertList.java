package com.vgoryashko.collectionslite.convertlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class that converts two-dimensioning arrays of int to a List<Integer> and vice verse.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/23/17
 */
public class ConvertList {

    /**
     * Method that converts two-dimensioning arrays of int to a List<Integer>.
     *
     * @param array array to be converted.
     * @return <code>List<Integer></code>
     */
    public List<Integer> toList(int[][] array) {

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                list.add(array[i][j]);
            }
        }
        return list;
    }

    /**
     * Method that converts a List<Integer> two-dimensioning arrays of int.
     *
     * @param  list to be converted.
     * @param rows number of rows in a new array.
     * @return <code>int[][]</code>.
     */
    public int[][] toArray(List<Integer> list, int rows) {

        int[][] array = new int[rows][];

        if (list.size() % rows != 0) {
            do {
                list.add(0);
            } while (list.size() % rows != 0);
        }

        int qtyInRow = list.size() / rows;

        Iterator<Integer> iterator = list.iterator();

        for (int i = 0; i < rows; i++) {
            array[i] = new int[qtyInRow];
            for (int j = 0; j < qtyInRow; j++) {
                if (iterator.hasNext()) {
                    array[i][j] = iterator.next();
                }
            }
        }

        return array;
    }
}

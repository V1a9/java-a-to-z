package com.vgoryashko.collectionspro.iteratortwodimensioningarray;

import java.util.Iterator;

/**
 * Class that implements Iterator for a two-dimensioning array.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 5/21/17
 */
public class IteratorTwoDimensioning implements Iterator<Integer> {

    /**
     * Variable that is used as an index for rows in an array.
     */
    private int row = 0;

    /**
     * Variable that is used as an index for columns in an array.
     */
    private int column = 0;

    /**
     * Constructor for the class.
     * @param array of integer
     */
    public IteratorTwoDimensioning(int[][] array) {
        this.array = array;
    }

    /**
     * Variable that is referring to an array.
     */
    private final int[][] array;

    /**
     * Method that checks if an array has a next element.
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        return array.length > row && array[row].length > column;
    }

    /**
     * Method that returns a next element in an array.
     * @return integer value
     */
    @Override
    public Integer next() {

        Integer result = null;

        if (column < array[row].length && row < array.length) {
            result = array[row][column++];
            if (column == array[row].length) {
                column = 0;
                row++;
            }
        }

        return result;
    }
}

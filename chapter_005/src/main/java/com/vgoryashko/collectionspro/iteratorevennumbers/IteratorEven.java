package com.vgoryashko.collectionspro.iteratorevennumbers;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class that implements iterator that returns even numbers.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 21.05.2017
 */
public class IteratorEven implements Iterator<Integer> {

    /**
     * Variable that referring to a ArrayList where even numbers should be returned from.
     */
    private final List<Integer> list;

    /**
     * Variable that is used as index for moving through the ArrayList.
     */
    private int index = 0;

    /**
     * Constructor for the class.
     * @param list where even numbers should be returned from.
     */
    public IteratorEven(List<Integer> list) {
        this.list = list;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Integer next() throws NoSuchElementException {

        Integer result = null;

        for (int i = index; i < list.size(); i++) {

            if (list.get(i) % 2 == 0) {
                result = list.get(i);
                index = i;
                break;
            }
        }

        for (int i =  index; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                index = list.get(i);
                break;
            } else {
                index = list.size() - 1;
            }
        }

        return result;
    }
}

package com.vgoryashko.collectionspro.iteratoriterator;

import java.util.Iterator;

/**
 * Class that test implementation of Iterator of Iterators.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 23.05.2017
 */
public class IteratorIterator implements Iterator {

    /**
     * Variable that referring to Iterator<Iterator<Integer>>.
     */
    private final Iterator<Iterator<Integer>> iterator;

    private Iterator<Integer> current;

    /**
     * Constructor for the class.
     * @param iterator Iterator<Iterator<Integer>>
     */
    public IteratorIterator(Iterator<Iterator<Integer>> iterator) {
        this.iterator = iterator;
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

        boolean result = false;

        if (current == null && this.iterator.hasNext()) {
            current = this.iterator.next();
            result = true;
        } else if (current != null && current.hasNext()) {
            result = true;
        } else if (current != null && this.iterator.hasNext()) {
            current = this.iterator.next();
            result = true;
        }

        return result;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     */
    @Override
    public Integer next() {

        return current.next();
    }
}

package com.vgoryashko.collectionspro.iteratoriterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that test implementation of Iterator of Iterators.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 28.05.2017
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
            if (this.current.hasNext()) {
                result = true;
            }
        } else if (current.hasNext()) {
            result = true;
        } else if (!current.hasNext() && this.iterator.hasNext()) {
            current = this.iterator.next();
            if (this.current.hasNext()) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Returns the next element in the iteration.
     * @throws NoSuchElementException
     * @return the next element in the iteration
     */
    @Override
    public Integer next() throws NoSuchElementException {

        Integer result = -1;

        if (current == null && this.iterator.hasNext()) {
            current = this.iterator.next();
            if (this.current.hasNext()) {
                result = current.next();
            }
        } else if (current.hasNext()) {
            result = current.next();
        } else if (!current.hasNext() && this.iterator.hasNext()) {
            current = this.iterator.next();
            if (this.current.hasNext()) {
                result = current.next();
            }
        }

        if (result == -1) {
            throw new NoSuchElementException("There is no elements available.");
        }

        return result;
    }
}

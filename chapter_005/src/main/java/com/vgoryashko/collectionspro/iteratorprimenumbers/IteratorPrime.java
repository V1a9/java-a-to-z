package com.vgoryashko.collectionspro.iteratorprimenumbers;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class that implements an iterator that returns prime numbers.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 22.05.2017
 */
public class IteratorPrime implements Iterator<Integer> {

    /**
     * Variable that referring to a ArrayList where even numbers should be returned from.
     */
    private final List<Integer> list;

    /**
     * Variable that is used as index for moving through the ArrayList.
     */
    private int index;

    /**
     * Constructor for the class.
     * @param list where even numbers should be returned from.
     */
    public IteratorPrime(List<Integer> list) {
        this.list = list;
    }

    /**
     * Method that finds prime number in an array that doesn't have elements that equals i + j + 2 * i * j.
     *
     * @return boolean
     */
    public boolean findPrime() {

        boolean result = false;

        for (int i = index; i < list.size(); i++) {
            if (this.list.get(this.index) <= 1) {
                if (this.index < this.list.size()) {
                    index++;
                    continue;
                } else {
                    this.index = this.list.size();
                    break;
                }
            } else if (this.list.get(this.index) <= 3) {
                result = true;
                break;
            } else if (list.get(index) % 2 == 0 || list.get(index) % 3 == 0) {
                if (index < list.size()) {
                    index++;
                    continue;
                } else {
                    index = list.size();
                    break;
                }
            }

            int n = 5;
            while (n * n <= list.get(index)) {
                if (list.get(index) % n == 0 || list.get(index) % (n + 2) == 0) {
                    if (index < list.size()) {
                        index++;
                    } else {
                        index = list.size();
                        break;
                    }
                } else {
                    n += 6;
                }
            }

            result = true;
            break;
        }

        return result;
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
        return this.findPrime();
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Integer next() throws NoSuchElementException {

        Integer result = -1;

        if (this.findPrime()) {
            result = this.list.get(index);
            if (index < list.size() - 1) {
                index++;
            } else {
                index = list.size();
            }
        }

        return result;
    }
}

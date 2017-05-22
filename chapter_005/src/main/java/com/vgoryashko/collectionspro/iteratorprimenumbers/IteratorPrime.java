package com.vgoryashko.collectionspro.iteratorprimenumbers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class that implements an iterator that returns prime numbers.
 *
 * @author Vlad Goryashko
 * @version 0.2
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
     * Method that removes all elements that equals i + j + 2 * i * j from an array.
     *
     * @param index integer
     * @return List of remaining integers
     */
    public List<Integer> removeElements(int index) {

        List<Integer> tmpList = new ArrayList<>();

        for (int i = 1; i <= this.list.get(index); i++) {
            tmpList.add(i);
        }

        for (int i = 0; i < tmpList.size(); i++) {
            for (int j = i; j < tmpList.size(); j++) {
                for (int k = 0; k < tmpList.size(); k++) {
                    if (tmpList.get(k) == tmpList.get(i) + tmpList.get(j) + 2 * tmpList.get(i) * tmpList.get(j)) {
                        tmpList.remove(k);
                        break;
                    }
                }
            }
        }

        return tmpList;
    }

    /**
     * Method that finds prime number in an array that doesn't have elements that equals i + j + 2 * i * j.
     *
     * @param tmpList array where prime number is going to be found
     * @param index integer
     * @return boolean
     */
    public boolean findPrime(List<Integer> tmpList, int index) {

        boolean result = false;

        for (int i = 0; i < tmpList.size(); i++) {
            if (list.get(index) == 2 * tmpList.get(i) + 1) {
                result = true;
                break;
            }
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

        if (list.get(this.index) < 3) {
            for (int i = 1; i < list.size() && list.get(i) <= 3; i++) {
                this.index++;
            }
        }

        List<Integer> tmpList = removeElements(this.index);

        Integer result = -1;

        if (findPrime(tmpList, this.index)) {
            result = list.get(this.index);
        }

        if (this.index < list.size()) {

            this.index++;

            tmpList.clear();

            for (int i = this.index; i < list.size(); i++) {

                tmpList = removeElements(i);

                if (findPrime(tmpList, i)) {
                    this.index = i;
                    break;
                } else if (this.index == list.size() - 1) {
                    this.index = list.size();
                }
            }
        }

        return result;
    }
}

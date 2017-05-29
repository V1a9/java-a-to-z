package com.vgoryashko.collectionspro.dynamiclist;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class that implement dynamic list.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 28.05.2017
 */
public class DynamicList<T> implements Iterable<T>{

    private int size = 10;
    private int index = 0;

    private Object[] list;

    public DynamicList() {
        this.list = new Object[this.size];
    }

    public void add(T element) {
        if (this.index < this.size) {
            this.list[this.index++] = element;
        } else {
            this.list = Arrays.copyOf(this.list, this.size += this.size/2 + 1);
            this.list[this.index++] = element;
        }
    }

    /**
     * Iterator for the dynamic list.
     */
    private class IteratorDynamic<T> implements Iterator<T> {

        private int aIndex = 0;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return aIndex < ;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            return null;
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return null;
    }
}

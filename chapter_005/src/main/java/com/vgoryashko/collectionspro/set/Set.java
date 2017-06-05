package com.vgoryashko.collectionspro.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that implements Set collection.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 6/5/17
 *
 * @param <T> type of element to be managed by Set.
 */
public class Set<T> implements Iterable<T> {

    /**
     * Variable that stores a size of a list.
     */
    private int size = 10;

    /**
     * variable that is used as index to add elements.
     */
    private int index = 0;

    /**
     * Variable that referring to an array of objects.
     */
    private Object[] set;

    /**
     * Constructor for the class.
     */
    public Set() {
        this.set = new Object[this.size];
    }

    /**
     * Method that adds an element to a set.
     *
     * @param element to be added
     */
    public void add(T element) {

        boolean exist = false;

        Iterator<T> iterator = this.iterator();

        while (iterator.hasNext()) {

            if (iterator.next().equals(element)) {
                exist = true;
                break;
            }
        }

        if (!exist) {

            if (this.index < this.size) {
                this.set[this.index++] = element;
            } else {
                this.size += this.size / 2 + 1;
                this.set = Arrays.copyOf(this.set, this.size);
                this.set[this.index++] = element;
            }
        }

    }

    /**
     * Getter for the member size.
     *
     * @return {@code int}
     */
    public int getSize() {
        return size;
    }

    /**
     * Getter for the member set.
     * @return {@code Object[]}
     */
    public Object[] getSet() {
        return set;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private int aIndex = 0;

            private Object[] aSet = getSet();

            @Override
            public boolean hasNext() {
                return getSize() > this.aIndex && this.aSet[aIndex] != null;
            }

            @Override
            public T next() {
                if ((T) aSet[aIndex] == null) {
                    throw new NoSuchElementException("There is no element available.");
                } else {
                    return (T) aSet[aIndex++];
                }
            }
        };
    }
}

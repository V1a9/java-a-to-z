package com.vgoryashko.collectionspro.dynamiclist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that implement dynamic list ThreadSafe.
 *
 * @param <T> an object to be used as parameter.
 * @author Vlad Goryashko
 * @version 0.6
 * @since 14.09.2017
 */
@ThreadSafe
public class DynamicList<T> implements Iterable<T> {

    /**
     * Variable that stores a size of a list.
     */
    private int size = 10;

    /**
     * variable that is used as index in a list to access elements.
     */
    private int index = 0;

    /**
     * Variable that referring to an array of objects.
     */
    @GuardedBy("this") private Object[] list;

    /**
     * Constructor for the class.
     */
    public DynamicList() {
        this.list = new Object[this.size];
    }

    /**
     * Method that adds an element to a list.
     *
     * @param element to be added
     */
    public synchronized void add(T element) {
        if (this.index < this.size) {
            this.list[this.index++] = element;
        } else {
            this.size += this.size / 2 + 1;
            this.list = Arrays.copyOf(this.list, this.size);
            this.list[this.index++] = element;
        }
    }

    /**
     * Getter for an element from a list.
     *
     * @param index of an element ro be returned.
     * @return {@code T}
     */
    public synchronized T get(int index) {

        return (T) this.list[index];

    }

    /**
     * Getter for the member size.
     *
     * @return {@code int}
     */
    public synchronized int getSize() {
        return size;
    }

    /**
     * Getter for the member list.
     * @return {@code Object[]}
     */
    public Object[] getList() {
        return list;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public synchronized Iterator<T> iterator() {

        return new Iterator<T>() {

            private int aIndex = 0;

            private Object[] aList = getList();

            @Override
            public boolean hasNext() {
                return getSize() > this.aIndex && this.aList[aIndex] != null;
            }

            @Override
            public T next() {
                if ((T) aList[aIndex] == null) {
                    throw new NoSuchElementException("There is no element available.");
                } else {
                    return (T) aList[aIndex++];
                }
            }
        };
    }
}
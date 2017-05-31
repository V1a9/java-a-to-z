package com.vgoryashko.collectionspro.dynamiclinkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that implement dynamic linked list.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 5/31/17
 *
 * @param <T> type of objects to be used with the class.
 */
public class DynamicLinkedList<T> implements Iterable<T> {

    /**
     * Variable that stores a size of a container.
     */
    private int size = 0;

    /**
     * Variable that stores first element of collection.
     */
    private Node<T> first;

    /**
     * Variable that stores pointer to current element.
     */
    private Node<T> current = first;

    /**
     * Variable that stores last element of collection.
     */
    private Node<T> last;

    /**
     * Class that is used as an element of the container.
     *
     * @param <T> type of object to be stored in an element.
     */
    private class Node<T> {

        /**
         * Variable that referring to element.
         */
        private T item;

        /**
         * Variable that stores pointer to next node.
         */
        private Node<T> pointerNext;

        /**
         * Variable that stores pointer to previous node.
         */
        private Node<T> pointerPrevious;

        /**
         * Constructor for the class.
         * @param pointerPrevious stores pointer to previous node
         * @param item item to be stored
         * @param pointerNext stores pointer to next node
         */
        Node(Node<T> pointerPrevious, T item, Node<T> pointerNext) {

            this.item = item;
            this.pointerNext = pointerNext;
            this.pointerPrevious = pointerPrevious;

        }
    }

    /**
     * Method that adds elements to collection.
     *
     * @param element element to be added
     */
    public void add(T element) {

        if (size == 0) {

            first = new Node<>(null, element, null);
            last = first;
            current = first;
            size++;

        } else {
            Node<T> newNode = new Node<>(last, element, null);
            last.pointerNext = newNode;
            last = newNode;
            size++;
        }

    }

    /**
     * Method that get an element based on an index.
     *
     * @param position position of element to be retrieved
     * @return {@code T}
     */
    public T get(int position) {

        T result = null;

        Iterator<T> iterator = this.iterator();

        int i = 0;
        this.current = this.first;
        while (iterator.hasNext()) {
            this.current = this.current.pointerNext;
            if (i++ == position - 1) {
                result = iterator.next();
                break;
            }

        }

        return result;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {

                T result = null;

                if (current != null) {
                    result = current.item;
                    current = current.pointerNext;
                    index++;
                }

                if (result == null) {
                    throw new NoSuchElementException("There is no element available.");
                }

                return result;
            }
        };
    }
}

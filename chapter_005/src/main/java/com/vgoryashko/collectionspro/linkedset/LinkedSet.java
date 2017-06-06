package com.vgoryashko.collectionspro.linkedset;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that implement Set on linked set.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 6/6/17
 *
 * @param <T> type of objects to be stored.
 */
public class LinkedSet<T> implements Iterable<T> {

    /**
     * Variable that stores size of the Set.
     */
    private int size = 0;

    /**
     * Variable that stores reference to a first node in the list.
     */
    private Node<T> first;

    /**
     * Variable that stores reference to a last node in the list.
     */
    private Node<T> last;

    /**
     * Class that defines node.
     *
     * @param <T> type a node will store.
     */
    private class Node<T> {

        /**
         * Value to be stored by each node.
         */
        private T item;

        /**
         * Variable that stores pointer to a next node.
         */
        private Node<T> next;

        /**
         * Variable that stores pointer to a previous node.
         */
        private Node<T> previous;

        /**
         * Constructor for the class.
         * @param previous stores pointer to previous node
         * @param item item to be stored
         * @param next stores pointer to next node
         */
        Node(Node<T> previous, T item, Node<T> next) {

            this.previous = previous;
            this.item = item;
            this.next = next;

        }

    }

    /**
     * Method that adds elements to collection.
     *
     * @param element element to be added
     */
    public void add(T element) {

        boolean exists = false;

        Iterator<T> iterator = this.iterator();

        while (iterator.hasNext()) {

            if (iterator.next().equals(element)) {

                exists = true;
                break;

            }
        }

        if (!exists) {

            if (this.size == 0) {

                this.first = new Node<>(null, element, null);
                this.last = this.first;
                this.size++;

            } else {
                Node<T> newNode = new Node<>(last, element, null);
                this.last.next = newNode;
                this.last = newNode;
                this.size++;
            }
        }
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
            private Node<T> current = first;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {

                T result = null;

                if (current == null) {
                    current = first;
                }

                if (current != null) {
                    result = current.item;
                    current = current.next;
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

package com.vgoryashko.collectionspro.linkedset;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that implement Set on linked set.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 6/12/17
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
     * Variable that stores pointer to a mid element.
     */
    private Node<T> midElement;

    /**
     * Variable that stores a pointer to left node.
     */
    private Node<T> left;

    /**
     * Variable that stores a pointer to right node.
     */
    private Node<T> right;

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
         *
         * @param previous stores pointer to previous node
         * @param item     item to be stored
         * @param next     stores pointer to next node
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

        int mid;
        boolean exist = false;

        int leftOffset = 0;
        int rightOffset = size;

        if (size == 0) {
            first = new Node<>(null, element, null);
            last = first;
            left = first;
            right = first;
            size++;

        } else if (left.equals(right)) {

            if (element.hashCode() < left.item.hashCode()) {

                Node<T> newNode = new Node<>(null, element, left);
                left.previous = newNode;
                left = newNode;
                first = newNode;
                size++;

            } else if (element.hashCode() > right.item.hashCode()) {

                Node<T> newNode = new Node<>(right, element, null);
                right.next = newNode;
                right = newNode;
                last = newNode;
                size++;

            }

        } else if (element.hashCode() < left.item.hashCode()) {

            Node<T> newNode = new Node<>(null, element, left);
            left.previous = newNode;
            left = newNode;
            first = newNode;
            size++;

        } else if (element.hashCode() > right.item.hashCode()) {

            Node<T> newNode = new Node<>(right, element, null);
            right.next = newNode;
            right = newNode;
            last = newNode;
            size++;

        } else {

            midElement = first;

            boolean forward = true;

            while (leftOffset < rightOffset) {

                mid = (rightOffset - leftOffset) / 2;

                for (int i = 0; i < mid; i++) {

                    if (forward) {

                        midElement = midElement.next;

                    } else {

                        midElement = midElement.previous;

                    }
                }

                if (element.hashCode() == midElement.item.hashCode()) {

                    exist = true;
                    break;

                } else if (element.hashCode() < midElement.item.hashCode()) {

                    forward = false;
                    rightOffset = mid;

                } else if (element.hashCode() > midElement.item.hashCode()) {

                    forward = true;
                    leftOffset = mid + 1;

                }

            }

            if (!exist) {

                if (element.hashCode() < midElement.item.hashCode()) {

                    Node<T> newNode = new Node<>(midElement.previous, element, midElement);
                    midElement.previous.next = newNode;
                    midElement.previous = newNode;
                    size++;

                } else {

                    Node<T> newNode = new Node<>(midElement, element, midElement.next);
                    midElement.next.previous = newNode;
                    midElement.next = newNode;
                    size++;

                }
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

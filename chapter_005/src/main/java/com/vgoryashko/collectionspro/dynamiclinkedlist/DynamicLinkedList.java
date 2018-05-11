package com.vgoryashko.collectionspro.dynamiclinkedlist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that implement dynamic linked list (added methods that support Stack and Queue).
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 13/09/17
 *
 * @param <T> type of objects to be used with the class.
 */
@ThreadSafe
public class DynamicLinkedList<T> implements Iterable<T> {

    /**
     * Variable that stores a size of a container.
     */
    private int size = 0;

    /**
     * Variable that stores first element of collection.
     */
    @GuardedBy("this") private Node<T> first;

    /**
     * Variable that stores last element of collection.
     */
    @GuardedBy("this") private Node<T> last;

    /**
     * Variable that stores pointer to current element.
     */
    @GuardedBy("this") private Node<T> current = first;

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
    public synchronized void add(T element) {

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
    public synchronized T get(int position) {

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
     * Method that pushes element onto the top of Stack.
     *
     * @param element element to be pushed onto top of stack.
     */
    public synchronized void push(T element) {

        if (size == 0) {
            last = new Node<>(null, element, null);
            size++;
        } else {
            Node<T> newNode = new Node<>(last, element, null);
            last.pointerNext = newNode;
            last = newNode;
            size++;
        }

    }

    /**
     * Method looks at the object at the top of Stack.
     *
     * @return {@code T}
     * @throws EmptyStackException if stack is empty.
     */
    public synchronized T peek() throws EmptyStackException {

        if (size > 0) {
            return last.item;
        } else {
            throw new EmptyStackException();
        }
    }

    /**
     * Method that checks if stack is empty.
     *
     * @return {@code boolean}
     */
    public boolean empty() {

        return size > 0;

    }

    /**
     * Method that removes object at the top of the stack.
     *
     * @return {@code T} object at the top of the stack.
     * @throws EmptyStackException if stack is empty.
     */
    public synchronized T pop() {

        T result = null;

        if (size > 0) {
            result = last.item;
            last = last.pointerPrevious;
            last.pointerNext = null;
            size--;

        } else {
            throw new EmptyStackException();
        }

        return result;

    }

    /**
     * Method that retrieves but not removes the head of queue (Queue).
     *
     * @return {@code T}
     */
    public synchronized T element() {

        if (size > 0) {
            return first.item;
        } else {
            throw new NoSuchElementException("There is no element available");
        }
    }

    /**
     * Method that removes the head of queue (Queue).
     *
     * @return {@code T}
     */
    public synchronized T remove() {

        T result;

        if (size == 0) {
            throw new NoSuchElementException("There is no element available.");
        } else {

            result = first.item;
            first = first.pointerNext;
            first.pointerPrevious = null;
            size--;
        }

        return result;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public synchronized Iterator<T> iterator() {

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
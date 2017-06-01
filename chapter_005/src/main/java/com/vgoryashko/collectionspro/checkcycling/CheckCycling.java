package com.vgoryashko.collectionspro.checkcycling;

/**
 * Class that checks cycling in linked list.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 01.06.2017
 */

public class CheckCycling<T> {

    class Node<T> {

        private T value;

        private Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

    }

    public boolean hasCycling(T element) {
        return false;
    }

}

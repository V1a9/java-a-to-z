package com.vgoryashko.collectionspro.checkcycling;

/**
 * Class that defines Node class for linked list.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 6/1/17
 */
public class Node<T> {

    private T value;

    Node<T> next;

    Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public void setNext(Node<T> node) {
        this.next = node;
    }
}

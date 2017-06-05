package com.vgoryashko.collectionspro.checkcycling;

/**
 * Class that defines Node class for linked list.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 6/1/17
 *
 * @param <T> type of parameter to be used.
 */
public class Node<T> {

    /**
     * Variable that stores any value of type <T>.
     */
    private T value;

    /**
     * Variable that referring to a next Node in list.
     */
    private Node<T> next;

    /**
     * Constructor for the class.
     *
     * @param value to be stored.
     * @param next pointer to a next node in the list.
     */
    Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    /**
     * Setter for the member next.
     *
     * @param node pointer to next node.
     */
    public void setNext(Node<T> node) {
        this.next = node;
    }

    /**
     * Getter for that member next.
     *
     * @return Node<T>
     */
    public Node<T> getNext() {
        return next;
    }


}

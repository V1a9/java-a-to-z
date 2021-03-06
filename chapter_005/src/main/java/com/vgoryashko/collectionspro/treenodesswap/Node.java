package com.vgoryashko.collectionspro.treenodesswap;

/**
 * Class that defines a tree structure.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 03.08.2017
 *
 * @param <T> to be used within the class.
 */

public class Node<T> {

    /**
     * Variable that referring to a left node.
     */
    private Node<T> left;

    /**
     * Variable that referring to a right node.
     */
    private Node<T> right;

    /**
     * Variable that stores a value of a node.
     */
    private T value;

    /**
     * Variable that stores boolean value for the flag if a node swapped or not.
     */
    private boolean swapped = false;

    /**
     * Variable that stores boolean value for the flag if a node was visited or not.
     */
    private boolean visited;

    /**
     * Constructor for the class.
     *
     * @param left left node
     * @param right right node
     * @param value of a node
     */
    public Node(Node<T> left, Node<T> right, T value) {
        this.left = left;
        this.right = right;
        this.value = value;
        this.swapped = false;
        this.visited = false;
    }

    /**
     * Getter for the member value.
     *
     * @return a value of a node
     */
    public T getValue() {
        return value;
    }

    /**
     * Getter for the member left.
     *
     * @return Node
     */
    public Node<T> getLeft() {
        return left;
    }

    /**
     * Setter for the member left.
     * @param left node
     */
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    /**
     * Getter for the member right.
     *
     * @return Node
     */
    public Node<T> getRight() {
        return right;
    }

    /**
     * Setter for the member right.
     * @param right node
     */
    public void setRight(Node<T> right) {
        this.right = right;
    }

    /**
     * Getter for the member swapped.
     *
     * @return boolean
     */
    public boolean isSwapped() {
        return swapped;
    }

    /**
     * Setter for the member swapped.
     *
     * @param swapped flag that defines if a node was swapped
     */
    public void setSwapped(boolean swapped) {
        this.swapped = swapped;
    }

    /**
     * Setter for the member visited.
     *
     * @param visited true if a node was visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Getter for the member visited.
     *
     * @return boolean
     */
    public boolean getVisited() {
        return visited;
    }
}

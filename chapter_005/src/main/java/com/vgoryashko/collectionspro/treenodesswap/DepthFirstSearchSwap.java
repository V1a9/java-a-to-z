package com.vgoryashko.collectionspro.treenodesswap;

import java.util.Stack;

/**
 * Class that implements method that traverses a tree based on depth-first search algorithm
 * and swaps left and right nodes.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 03.08.2017
 *
 * @param <T> to be used within the class.
 */

public class DepthFirstSearchSwap<T> {

    /**
     * Variable that referring to a root node.
     */
    private Node<T> root;

    /**
     * Variable that is referring to a current node.
     */
    private Node<T> current;

    /**
     * Variable that is referring to a next Node.
     */
    private Node<T> next;

    /**
     * Constructor for the class.
     */
    public DepthFirstSearchSwap(Node<T> node) {

        this.root = node;
        this.next = this.root;

    }

    /**
     * Variable that is referring to a stack of Node's pointers.
     */
    private Stack<Node<T>> stack = new Stack<>();

    /**
     * Method that swaps nodes with stack.
     */
    public void depthFirstSearchSwap() {

        this.current = this.next;

        if (this.current != root || !this.current.isSwapped()) {

            this.stack.push(this.current);

            if (this.current.getLeft() != null) {

                this.next = this.current.getLeft();

//                Node<T> tmp = this.current.getLeft();
//                this.current.setLeft(this.current.getRight());
//                this.current.setRight(tmp);
//                this.current.setSwapped(true);

                depthFirstSearchSwap();

            } else if (this.current.getRight() != null) {

                this.stack.push(this.current.getRight());
                this.next = this.current.getLeft();

            }

        }

    }

}

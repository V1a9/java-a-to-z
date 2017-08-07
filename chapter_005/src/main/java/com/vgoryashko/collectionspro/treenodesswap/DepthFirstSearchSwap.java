package com.vgoryashko.collectionspro.treenodesswap;

import java.util.Stack;

/**
 * Class that implements method that traverses a tree based on depth-first search algorithm
 * and swaps left and right nodes.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 07.08.2017
 *
 * @param <T> to be used within the class.
 */

public class DepthFirstSearchSwap<T> {

    /**
     * Variable that is referring to a current node (initial value is a root node).
     */
    private Node<T> current;

    /**
     * Variable that is referring to a stack of Node's pointers.
     */
    private Stack<Node<T>> stack = new Stack<>();

    /**
     * Constructor for the class.
     */
    public DepthFirstSearchSwap(Node<T> node) {

        this.current = node;
        this.stack.push(this.current);
        this.current.setVisited(true);

    }

    /**
     * Method that swaps nodes with stack.
     */
    public void depthFirstSearchSwap() {

        if (!this.stack.empty()) {

            this.current = this.stack.peek();

            if (this.current.getLeft() != null && !this.current.getLeft().getVisited()) {

                this.stack.push(this.current.getLeft());
                this.current.getLeft().setVisited(true);
                depthFirstSearchSwap();

            } else if (this.current.getRight() != null && !this.current.getRight().getVisited()) {

                this.stack.push(this.current.getRight());
                this.current.getRight().setVisited(true);
                depthFirstSearchSwap();

            } else {

                this.stack.pop();

                if (!this.stack.isEmpty()) {

                    this.current = this.stack.peek();

                }

                if (this.current.getRight().getVisited() && !this.current.isSwapped()) {

                    Node<T> tmp = this.current.getLeft();
                    this.current.setLeft(this.current.getRight());
                    this.current.setRight(tmp);
                    this.current.setSwapped(true);

                }

                depthFirstSearchSwap();

            }

        }

    }

}

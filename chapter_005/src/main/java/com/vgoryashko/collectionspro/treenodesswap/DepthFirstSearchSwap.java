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
     * Variable that is referring to a stack of Node's pointers.
     */
    private Stack<Node<T>> stack = new Stack<>();

    /**
     * Constructor for the class.
     */
    public DepthFirstSearchSwap(Node<T> node) {

        this.root = node;
        this.next = this.root;
        this.stack.push(this.next);

    }

    /**
     * Method that swaps nodes with stack.
     */
    public void depthFirstSearchSwap() {

        if (!this.stack.empty()) {

            this.current = this.stack.peek();

            while (this.current.getLeft() != null) {

                this.stack.push(this.current.getLeft());
                this.current = this.stack.peek();

            }

            if (this.current.getRight() == null) {

                this.current = this.stack.pop();

            }

        }



//                Node<T> tmp = this.current.getLeft();
//                this.current.setLeft(this.current.getRight());
//                this.current.setRight(tmp);
//                this.current.setSwapped(true);

    }

}

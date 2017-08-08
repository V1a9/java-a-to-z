package com.vgoryashko.collectionspro.treenodesswap;

import java.util.*;

/**
 * Class that implements method that traverses a tree based on breadth-first search algorithm
 * and swaps left and right nodes.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 08.08.2017
 *
 * @param <T> to be used within the class.
 */

public class BreadthFirstSearchSwap<T> {

    /**
     * Variable that is referring to a current node (initial value is a root node).
     */
    private Node<T> current;

    /**
     * Variable that is referring to a stack of Node's pointers.
     */
    private Queue<Node<T>> queue = new LinkedList<>();

    /**
     * Constructor for the class.
     */
    public BreadthFirstSearchSwap(Node<T> node) {

        this.current = node;
        this.queue.add(node);
        this.current.setVisited(true);

    }

    /**
     * Method that swaps nodes with stack.
     */
    public void breadthFirstSearchSwap() {

        if (this.queue.size() != 0) {

            this.current = this.queue.remove();

            if (this.current.getLeft() != null) {

                this.queue.add(this.current.getLeft());
                this.current.getLeft().setVisited(true);

            } else if (this.current.getRight() != null) {

                this.queue.add(this.current.getRight());
                this.current.getRight().setVisited(true);

            }

            if ((this.current.getLeft() != null && this.current.getLeft().getVisited()) ||
                    this.current.getRight() != null && this.current.getRight().getVisited()) {

                Node<T> tmp = this.current.getLeft();
                this.current.setLeft(this.current.getRight());
                this.current.setRight(tmp);
                this.current.setSwapped(true);

            }

            breadthFirstSearchSwap();


        }

    }

}

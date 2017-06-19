package com.vgoryashko.collectionspro.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that implements simple tree data structure.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 6/17/17
 *
 * @param <E> type of elements to be stored.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * Variable that is referring to an instance of Node.
     */
    private Node root;

    /**
     * Class that represents a node of the tree.
     * @param <E> type of elements to be stored
     */
    private class Node<E> {
        /**
         * List of child nodes in the node.
         */
        List<Node<E>> children = new LinkedList<>();

        /**
         * Variable that stores a value.
         */
        E value;

        /**
         * Constructor for the class.
         *
         * @param value to be stored in the node.
         */
        Node(E value) {
            this.value = value;
        }
    }

    /**
     * Method that adds an element into the tree.
     * @param parent parent.
     * @param child child.
     * @return {@code boolean}
     */
    @Override
    public boolean add(E parent, E child) {

        boolean result = false;

        if (root == null) {

            root = new Node(child);
            result = true;

        } else {



        }

        return result;
    }

    /**
     * Method that implements iterator for the tree.
     *
     * @return Iterator()
     */
    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            Node current = root;
            Node branch = root;
            int index = 0;

            @Override
            public boolean hasNext() {

                boolean result = false;

                if (current != null) {

                    result = true;

                } else

                if (!current.children.isEmpty()) {

                    current = current.children.get(index);

                }
                return result;
            }

            @Override
            public E next() {
                return null;
            }
        };
    }
}
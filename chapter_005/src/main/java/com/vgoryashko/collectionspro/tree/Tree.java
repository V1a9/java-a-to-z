package com.vgoryashko.collectionspro.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class that implements simple tree data structure.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 7/14/17
 *
 * @param <E> type of elements to be stored.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * Variable that is referring to an instance of Node.
     */
    private Node root;

    /**
     * Variable that is referring to an instance of a current Node.
     */
    private Node current;

    private Node next;

    /**
     * Class that represents a node of the tree.
     * @param <E> type of elements to be stored
     */
    private class Node<E> {
        /**
         * List of child nodes in the node.
         */
        List<Node<E>> children = new ArrayList<>();

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

        Iterator<E> iterator = this.iterator();

        if (parent == null) {

            root = new Node(child);
            next = root;
            result = true;

        } else {

            while (iterator.hasNext()) {

                if (iterator.next().compareTo(parent) == 0) {

                    current.children.add(new Node(child));
                    result = true;
                    current = root;
                    break;

                }

            }

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

        current = next;

        return new Iterator<E>() {

            Iterator<Node<E>> iteratorChildrenNext = null;

            @Override
            public boolean hasNext() {

                boolean result = false;

                if (current != null) {

                    result = true;

                } else if (current == root) {

                    result = true;

                } else if () {



                }

                return result;
            }

            @Override
            public E next() throws NoSuchElementException {

                current = next;
                E result;

                if (current == null) {

                    throw new NoSuchElementException("There is no elements.");

                } else {

                    result = (E) current.value;

                    if (current.children.isEmpty()) {

                    } else {

                        if (iteratorChildrenNext == null) {

                            iteratorChildrenNext = current.children.iterator();

                        }

                        if (iteratorChildrenNext.hasNext()) {

                            next = iteratorChildrenNext.next();

                        }
                    }
                }

                return result;
            }
        };
    }
}
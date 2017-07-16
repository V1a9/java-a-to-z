package com.vgoryashko.collectionspro.tree;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.NoSuchElementException;

/**
 * Class that implements simple tree data structure.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 7/16/17
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

    /**
     * Variable that is referring to a next Node.
     */
    private Node next;

    /**
     * Class that represents a node of the tree.
     * @param <E> type of elements to be stored
     */
    private class Node<E> {

        /**
         * List of child nodes in the node.
         */
        private List<Node<E>> children = new ArrayList<>();

        /**
         * Variable that stores a value.
         */
        private E value;

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
                    next = root;
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

            private Iterator<Node<E>> iteratorChildrenNext = null;
            private Stack<Iterator<Node<E>>> vertexIteratorStack = new Stack<>();

            @Override
            public boolean hasNext() {

                boolean result = false;
                current = next;

                vertexIteratorStack.push(current.children.iterator());
                iteratorChildrenNext = vertexIteratorStack.peek();

                if (iteratorChildrenNext.hasNext()) {

                    result = true;

                } else {

                    while (!vertexIteratorStack.empty()) {

                        iteratorChildrenNext = vertexIteratorStack.peek();

                        if (iteratorChildrenNext.hasNext()) {

                            result = true;
                            break;

                        } else {

                            vertexIteratorStack.pop();

                        }

                    }

                    if (vertexIteratorStack.empty()) {

                        result = false;

                    }

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

                    vertexIteratorStack.push(current.children.iterator());
                    iteratorChildrenNext = vertexIteratorStack.peek();

                    if (iteratorChildrenNext.hasNext()) {

                        next = iteratorChildrenNext.next();

                    } else {

                        while (!vertexIteratorStack.empty()) {

                            iteratorChildrenNext = vertexIteratorStack.peek();

                            if (iteratorChildrenNext.hasNext()) {

                                next = iteratorChildrenNext.next();
                                break;

                            } else {

                                vertexIteratorStack.pop();

                            }

                        }

                        if (vertexIteratorStack.empty()) {

                            next = null;

                        }

                    }
                }

                return result;
            }
        };
    }
}
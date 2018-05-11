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
 * @version 0.8
 * @since 7/18/17
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
     * Variable that is referring to a stack of iterators used in the isBinary() method.
     */
    private Stack<Iterator<Node<E>>> vertexIteratorStackBin = new Stack<>();

    /**
     * Class that represents a node of the tree.
     * @param <E> type of elements to be stored
     */
    private class Node<E> {

        /**
         * List of child nodes in the node.
         */
        private List<Node<E>> children = new ArrayList<>(2);

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
     * @param element element to be added
     * @return {@code boolean}
     */
    @Override
    public boolean add(E element) {

        boolean result = false;

        if (root == null) {

            root = new Node(element);
            next = root;
            result = true;

        } else {

            current = next;

            if (element.compareTo((E) current.value) == -1) {

                if (current.children.size() == 0) {

                    current.children.add(new Node(element));
                    next = root;
                    return true;

                } else if (current.children.size() != 0 && current.children.size() <= 2 && current.children.get(0) == null) {

                    current.children.remove(0);
                    current.children.add(0, new Node(element));
                    next = root;
                    return true;

                } else {

                    next = (Node<E>) current.children.get(0);
                    return add(element);

                }

            } else if (element.compareTo((E) current.value) == 1) {

                if (current.children.size() == 2 && current.children.get(1) == null) {

                    current.children.add(1, new Node(element));
                    next = root;
                    return true;

                } else if (current.children.size() == 1) {

                    current.children.add(new Node(element));
                    next = root;
                    return true;

                } else if (current.children.size() == 0) {

                    current.children.add(null);
                    current.children.add(new Node(element));
                    next = root;
                    return true;

                } else {

                    next = (Node<E>) current.children.get(1);
                    return add(element);

                }

            }

        }

        return result;
    }

    /**
     * Method that pops iterators from the stack and sets pointer to a next Node.
     * @param stack that stores pointers to Iterators
     */
    public void popIterator(Stack<Iterator<Node<E>>> stack) {

        Iterator<Node<E>> iterator;

        while (!stack.empty()) {

            iterator = stack.peek();

            if (iterator.hasNext()) {

                next = iterator.next();
                break;

            } else {

                stack.pop();

            }

        }

    }

    /**
     * Method that checks if a tree is a binary tree.
     *
     * @return {@code boolean}
     */
    public boolean isBinary() {

        Iterator<Node<E>> iteratorChildrenNext;
        current = next;

        if (current.children.size() > 2) {
            return false;
        }

        vertexIteratorStackBin.push(current.children.iterator());
        iteratorChildrenNext = vertexIteratorStackBin.peek();

        if (iteratorChildrenNext.hasNext()) {

                Node<E> tmp = iteratorChildrenNext.next();

                if (tmp != null) {

                    next = tmp;

                } else if (iteratorChildrenNext.hasNext()) {

                    tmp = iteratorChildrenNext.next();

                    if (tmp != null) {

                        next = tmp;

                    }
                }

        } else {

            popIterator(vertexIteratorStackBin);

            if (vertexIteratorStackBin.empty()) {

                return true;

            }
        }

        return isBinary();

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

            private Stack<Iterator<Node<E>>> vertexIteratorStack = new Stack<>();
            private Iterator<Node<E>> iteratorChildrenNext;

            @Override
            public boolean hasNext() {

                boolean result;
                current = next;

                if (current != null) {

                    result = true;

                } else {

                    result = false;

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

                        popIterator(vertexIteratorStack);

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
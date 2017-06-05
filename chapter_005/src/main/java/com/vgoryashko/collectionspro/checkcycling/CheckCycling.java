package com.vgoryashko.collectionspro.checkcycling;

/**
 * Class that checks cycling in linked list.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 05.06.2017
 *
 * @param <T> type of parameter to be used.
 */

public class CheckCycling<T> {

    /**
     * Method that checks cycling in linked list.
     *
     * @param node to be tested (the 1st node in the list).
     * @return {@code boolean}
     */
    public boolean hasCycling(Node<T> node) {

        Node<T> currentNode;

        Node<T> runner;

        boolean result = false;

        if (node.getNext() == null) {

            result = false;

        } else if (node == node.getNext()) {

            result = true;

        } else {

            runner = node;

            currentNode = runner.getNext();

            while (currentNode.getNext() != null) {

                runner = node;

                while (runner != currentNode) {

                    if (runner == currentNode.getNext()) {

                        result = true;
                        break;

                    } else {

                        runner = runner.getNext();

                    }

                }

                if (!result) {

                    currentNode = currentNode.getNext();

                } else {

                    break;

                }

            }

        }

        return result;
    }

}

package com.vgoryashko.collectionspro.checkcycling;

/**
 * Class that checks cycling in linked list.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 01.06.2017
 */

public class CheckCycling<T> {

    public boolean hasCycling(Node<T> node) {

        Node<T> currentNode;

        Node<T> nextNode;

        boolean result = false;

        if (node.next == null) {

            result = false;

        } else if (node == node.next) {

            result = true;

        } else {

            currentNode = node;

            nextNode = node.next;

            while (currentNode != null) {

                while (nextNode.next != null) {

                    if (currentNode == nextNode.next) {

                        result = true;
                        break;

                    } else {

                        nextNode = nextNode.next;

                    }

                }

                if (result) {
                    break;
                } else {
                    currentNode = currentNode.next;
                }

            }

        }

        return result;
    }

}

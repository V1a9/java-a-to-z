package com.vgoryashko.collectionspro.checkcycling;

/**
 * Class that checks cycling in linked list.
 *
 * @author Vlad Goryashko
 * @version 0.4
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

        boolean result = false;

        Node<T> tortoise = node;

        Node<T> rabbit;

        if (tortoise.getNext() == null) {

            result = false;

        } else if (tortoise.equals(tortoise.getNext())) {

            result = true;

        } else if (tortoise.getNext().getNext() != null) {

            rabbit = tortoise.getNext().getNext();

            while (!rabbit.equals(tortoise)) {

                if (rabbit.getNext() != null && rabbit.getNext().getNext() != null) {

                    rabbit = rabbit.getNext().getNext();
                    tortoise = tortoise.getNext();

                } else {

                    result = false;
                    break;

                }

                result = true;

            }

        } else {

            result = false;

        }

        return result;
    }
}

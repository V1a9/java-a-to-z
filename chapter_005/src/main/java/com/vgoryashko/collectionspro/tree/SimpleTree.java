package com.vgoryashko.collectionspro.tree;

/**
 * Interface that implements simple tree data structure.
 *
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 6/17/17
 *
 * @param <E> type of elements to be stored in the collection.
 */
public interface SimpleTree <E extends Comparable<E>> extends Iterable<E> {

    /**
     * Method that adds an element into the collection.
     * Add child to parent.
     * Parent can have a list of childs.
     *
     * @param parent parent.
     * @param child child.
     * @return {@code boolean}
     */
    boolean add(E parent, E child);

}

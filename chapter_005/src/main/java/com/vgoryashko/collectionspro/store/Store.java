package com.vgoryashko.collectionspro.store;

/**
 * Interface that defines Store entity that allows store of Users.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 13.06.2017
 *
 * @param <T> type of parameter to be used
 */
public interface Store<T extends Base> {

    /**
     * Method that adds an User to the collection.
     *
     * @param element to be added.
     */
    void add(T element);

    /**
     * Method that updates an User in the collection based on the index.
     *
     * @param index of the element to be updated.
     * @param element to be added.
     */
    void update(int index, T element);

    /**
     * Method that removes element based on the index.
     *
     * @param index of the element to be removed.
     */
    void remove(int index);

    /**
     * Method that retrieves an element based on the index.
     *
     * @param index of the element to be retrieved.
     * @return T element to be returned.
     */
    T get(int index);
}

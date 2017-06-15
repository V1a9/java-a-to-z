package com.vgoryashko.collectionspro.store;

/**
 * Interface that defines Store entity that allows store of Users.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 15.06.2017
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
     * @param id of the element to be updated.
     * @param element to be added.
     */
    void update(String id, T element);

    /**
     * Method that removes element based on the index.
     *
     * @param id of the element to be removed.
     */
    void remove(String id);

    /**
     * Method that retrieves an element based on the index.
     *
     * @param id of the element to be retrieved.
     * @return T element to be returned.
     */
    T get(String id);
}

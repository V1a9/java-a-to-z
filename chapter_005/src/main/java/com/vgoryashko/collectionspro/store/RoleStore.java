package com.vgoryashko.collectionspro.store;

import com.vgoryashko.collectionspro.simplearray.SimpleArray;

/**
 * Class that implement Role Store collection.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 13.06.2017
 *
 * @param <T> type of parameter to be used
 */
public class RoleStore<T extends Role> implements Store<T> {

    /**
     * Variable that referring to an instance of SimpleArray class.
     */
    private SimpleArray<T> roleSimpleArray;

    /**
     * Constructor for the class.
     */
    public RoleStore() {
        this.roleSimpleArray = new SimpleArray<>(10);
    }

    /**
     * Method that adds an User to the collection.
     *
     * @param element to be added.
     */
    @Override
    public void add(T element) {
        roleSimpleArray.add(element);
    }

    /**
     * Method that updates an User in the collection based on the index.
     *
     * @param index of the element to be updated.
     * @param element to be added.
     */
    @Override
    public void update(int index, T element) {
        roleSimpleArray.update(index, element);
    }

    /**
     * Method that removes element based on the index.
     *
     * @param index of the element to be removed.
     */
    @Override
    public void remove(int index) {
        roleSimpleArray.delete(index);
    }

    /**
     * Method that retrieves an element based on the index.
     *
     * @param index of the element to be retrieved.
     * @return T element to be returned.
     */
    @Override
    public T get(int index) {
        return roleSimpleArray.get(index);
    }
}

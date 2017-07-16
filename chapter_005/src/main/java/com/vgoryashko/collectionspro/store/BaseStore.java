package com.vgoryashko.collectionspro.store;

import com.vgoryashko.collectionspro.simplearray.SimpleArray;

/**
 * Class that implements collection that stores Users.
 *
 * @author Vlad Goryashko
 * @version 0.7
 * @since 16.07.2017
 *
 * @param <T> type of parameter to be used
 */
public class BaseStore<T extends Base> implements Store<T> {

    /**
     * Variable that referring to an instance of SimpleArray class.
     */
    private SimpleArray<T> baseStore;

    /**
     * Constructor for the class.
     */
    public BaseStore() {
        this.baseStore = new SimpleArray<>(10);
    }

    /**
     * Method that adds an User to the collection.
     *
     * @param element to be added.
     */
    @Override
    public void add(T element) {
        baseStore.add(element);
    }

    /**
     * Method that updates an User in the collection based on the index.
     *
     * @param id of the element to be updated.
     * @param element to be added.
     */
    @Override
    public void update(String id, T element) {

        int index = 0;
        for (Object entry : baseStore.getSimpleArray()) {

            T element1 = (T) entry;

            if (element1.getId().equals(id)) {
                baseStore.update(index, element);
                break;
            }
            index++;
        }
    }

    /**
     * Method that removes element based on the index.
     *
     * @param id of the element to be removed.
     */
    @Override
    public void remove(String id) {

        int index = 0;

        for (Object entry : baseStore.getSimpleArray()) {
            T element = (T) entry;
            if (element.getId().equals(id)) {
                baseStore.delete(index);
                break;
            }
            index++;
        }
    }

    /**
     * Method that retrieves an element based on the index.
     *
     * @param id of the element to be retrieved.
     * @return T element to be returned.
     */
    @Override
    public T get(String id) {

        T result = null;
        int index = 0;

        for (Object entry : baseStore.getSimpleArray()) {
            T element = (T) entry;
            if (element != null && element.getId().equals(id)) {
                result = baseStore.get(index);
                break;
            }
            index++;
        }

        return result;
    }
}

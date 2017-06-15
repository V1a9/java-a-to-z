package com.vgoryashko.collectionspro.store;

import com.vgoryashko.collectionspro.simplearray.SimpleArray;

/**
 * Class that implements collection that stores Users.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 15.06.2017
 *
 * @param <T> type of parameter to be used
 */
public class UserStore<T extends User> implements Store<T> {

    /**
     * Variable that referring to an instance of SimpleArray class.
     */
    private SimpleArray<T> userStore;

    /**
     * Constructor for the class.
     */
    public UserStore() {
        this.userStore = new SimpleArray<>(10);
    }

    /**
     * Method that adds an User to the collection.
     *
     * @param element to be added.
     */
    @Override
    public void add(T element) {
        userStore.add(element);
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
        for (Object entry : userStore.getSimpleArray()) {
            User user = (User) entry;
            if (user.getId().equals(id)) {
                userStore.update(index, element);
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
        for (Object entry : userStore.getSimpleArray()) {
            User user = (User) entry;
            if (user.getId().equals(id)) {
                userStore.delete(index);
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
        for (Object entry : userStore.getSimpleArray()) {
            User user = (User) entry;
            if (user != null && user.getId().equals(id)) {
                result = userStore.get(index);
                break;
            }
            index++;
        }

        return result;
    }
}

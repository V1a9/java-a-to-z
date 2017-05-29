package com.vgoryashko.collectionspro.store;

import com.vgoryashko.collectionspro.simplearray.SimpleArray;

/**
 * Class that
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 24.05.2017
 */
public class UserStore<T extends User> {

    private SimpleArray<T> userSimpleArray;

    public UserStore() {
        this.userSimpleArray = new SimpleArray<>(10);
    }
}

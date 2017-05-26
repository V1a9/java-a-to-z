package com.vgoryashko.collectionspro.store;

import com.vgoryashko.collectionspro.simplearray.SimpleArray;

/**
 * Class that
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 24.05.2017
 */
public class RoleStore<T> {

    private SimpleArray<T> roleSimpleArray;

    public RoleStore() {
        this.roleSimpleArray = new SimpleArray<>(10);
    }
}

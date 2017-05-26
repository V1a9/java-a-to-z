package com.vgoryashko.collectionspro.store;

/**
 * Class that defines a contract for models.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 24.05.2017
 */
public abstract class Base {

    /**
     * Method that gets Id.
     *
     * @return {@code String}
     */
    public abstract String getId();

    /**
     * Method that sets Id.
     *
     * @param id String id to be set.
     */
    public abstract void setId(String id);
}

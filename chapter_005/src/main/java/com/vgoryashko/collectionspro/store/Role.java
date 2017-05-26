package com.vgoryashko.collectionspro.store;

/**
 * Class that defines Role class.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 24.05.2017
 */
public class Role extends Base {

    /**
     * Variable that stores Id value.
     */
    private String id;

    /**
     * Method that gets Id.
     *
     * @return {@code String}
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * Method that sets Id.
     *
     * @param id String id to be set.
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }
}

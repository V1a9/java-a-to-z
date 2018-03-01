package com.vgoryashko.hibernate.carsstore.models.items;

/**
 * Class that defeines general Item for different entities like Advert, Comment.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/01/18
 */
public abstract class Item {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

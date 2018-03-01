package com.vgoryashko.hibernate.carsstore.models.parts;

/**
 * Interface that defines general Part model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/01/18
 */
public abstract class Part {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}

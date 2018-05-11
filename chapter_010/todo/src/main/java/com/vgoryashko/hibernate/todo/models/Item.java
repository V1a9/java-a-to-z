package com.vgoryashko.hibernate.todo.models;

import java.sql.Timestamp;

/**
 * Class that defines the model Item.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/20/18
 */
public class Item {

    private int id;

    private String desc;

    private Timestamp created;

    private boolean done;

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

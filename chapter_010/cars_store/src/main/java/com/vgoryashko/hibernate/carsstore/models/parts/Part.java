package com.vgoryashko.hibernate.carsstore.models.parts;

import java.io.Serializable;

/**
 * Interface that defines general Part model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/01/18
 */
public class Part implements Serializable {

    private long id;

    private String type;

    private String description;

    public Part() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Part)) {
            return false;
        }

        Part part = (Part) o;

        if (!type.equals(part.type)) {
            return false;
        }
        return description.equals(part.description);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}

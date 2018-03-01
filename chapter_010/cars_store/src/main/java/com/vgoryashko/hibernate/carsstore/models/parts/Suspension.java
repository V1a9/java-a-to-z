package com.vgoryashko.hibernate.carsstore.models.parts;

/**
 * Class that extends Part and implements Suspension model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/01/18
 */
public class Suspension extends Part {

    private String description;

    public Suspension() {
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
        if (!(o instanceof Suspension)) {
            return false;
        }

        Suspension that = (Suspension) o;

        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}

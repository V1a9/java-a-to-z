package com.vgoryashko.hibernate.carsstore.models.parts;

/**
 * Class that extends Part and implements Transmission model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/01/18
 */
public class Transmission extends Part {

    private String description;

    public Transmission() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transmission)) {
            return false;
        }

        Transmission that = (Transmission) o;

        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}

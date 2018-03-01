package com.vgoryashko.hibernate.carsstore.models.parts;

/**
 * Class that extends Part and implements Engine model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/01/18
 */
public class Engine extends Part {

    private String description;

    public Engine() {
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
        if (!(o instanceof Engine)) {
            return false;
        }

        Engine engine = (Engine) o;

        return description.equals(engine.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}

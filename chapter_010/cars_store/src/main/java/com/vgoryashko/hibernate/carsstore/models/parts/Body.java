package com.vgoryashko.hibernate.carsstore.models.parts;

import com.vgoryashko.hibernate.carsstore.models.cars.Car;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that extends Part and implements Body model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/01/18
 */
public class Body extends Part {

    private String description;

    public Body() {
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
        if (!(o instanceof Body)) {
            return false;
        }

        Body body = (Body) o;

        return description.equals(body.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
    
}

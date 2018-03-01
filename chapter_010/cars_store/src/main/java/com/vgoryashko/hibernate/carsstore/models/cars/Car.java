package com.vgoryashko.hibernate.carsstore.models.cars;

import com.vgoryashko.hibernate.carsstore.models.items.Item;
import com.vgoryashko.hibernate.carsstore.models.parts.Part;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that implements Car.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/01/18
 */
public class Car {

    private long id;

    private String vin;

    private Item item;

    private Set<Part> parts = new HashSet<>();

    private String brand;

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }

        Car that = (Car) o;

        return vin.equals(that.vin);
    }

    @Override
    public int hashCode() {
        return vin.hashCode();
    }
}

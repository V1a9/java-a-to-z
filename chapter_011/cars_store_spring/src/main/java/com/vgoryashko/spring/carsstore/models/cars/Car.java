package com.vgoryashko.spring.carsstore.models.cars;

import com.vgoryashko.spring.carsstore.models.parts.Part;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements Car.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/01/18
 */
public class Car implements Serializable {

    private long id;

    private String vin;

    private List<Part> parts = new ArrayList<>();

    private String brand;

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
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

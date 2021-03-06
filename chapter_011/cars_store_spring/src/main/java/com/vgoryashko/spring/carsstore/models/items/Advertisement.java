package com.vgoryashko.spring.carsstore.models.items;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vgoryashko.spring.carsstore.models.cars.Car;
import com.vgoryashko.spring.carsstore.models.users.User;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements Advertisement model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/01/18
 */
public class Advertisement implements Serializable {

    private long id;

    private Timestamp created;

    private String description;

    private Car car;

    private int price;

    @JsonBackReference
    private User user;

    @JsonManagedReference
    private List<Photo> photos = new ArrayList<>();

    private boolean sold;

    public Advertisement() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Photo> getPhotos() {
        return this.photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Advertisement)) {
            return false;
        }

        Advertisement advertisement = (Advertisement) o;

        if (price != advertisement.price) {
            return false;
        }
        if (!description.equals(advertisement.description)) {
            return false;
        }
        if (!car.equals(advertisement.car)) {
            return false;
        }
        return user.equals(advertisement.user);
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + car.hashCode();
        result = 31 * result + price;
        result = 31 * result + user.hashCode();
        return result;
    }
}

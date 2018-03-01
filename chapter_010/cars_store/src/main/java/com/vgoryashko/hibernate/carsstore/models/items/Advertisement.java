package com.vgoryashko.hibernate.carsstore.models.items;

import com.vgoryashko.hibernate.carsstore.models.cars.Car;
import com.vgoryashko.hibernate.carsstore.models.users.User;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that implements Advertisement model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/01/18
 */
public class Advertisement<T> extends Item {

    private Timestamp created;

    private String description;

    private Car car;

    private int price;

    private User user;

    private Set<T> photos = new HashSet<>();

    private boolean sold;

    public Advertisement() {
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

    public Set<T> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<T> photos) {
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

        Advertisement<?> that = (Advertisement<?>) o;

        if (price != that.price) {
            return false;
        }
        if (!description.equals(that.description)) {
            return false;
        }
        if (!car.equals(that.car)) {
            return false;
        }
        return user.equals(that.user);
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

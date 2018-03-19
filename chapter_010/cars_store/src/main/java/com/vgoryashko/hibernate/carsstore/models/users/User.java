package com.vgoryashko.hibernate.carsstore.models.users;

import com.vgoryashko.hibernate.carsstore.models.items.Advertisement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements User model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/01/18
 */
public class User implements Serializable {

    private long id;

    private String name;

    private String login;

    private String password;

    private List<Advertisement> advertisements = new ArrayList<>();

    public User() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }
}

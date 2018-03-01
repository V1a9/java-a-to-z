package com.vgoryashko.hibernate.carsstore.models.users;

import com.vgoryashko.hibernate.carsstore.models.items.Item;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that implements User model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 3/01/18
 */
public class User {

    private long id;

    private String name;

    private String login;

    private String password;

    private Set<Item> items = new HashSet<>();

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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> item) {
        this.items = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User that = (User) o;

        if (!name.equals(that.name)) {
            return false;
        }
        return login.equals(that.login);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + login.hashCode();
        return result;
    }
}

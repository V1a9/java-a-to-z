package com.vgoryashko.spring.models;

/**
 * Class that defines an User model.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/29/18
 */
public class User {

    private String name;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        return name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

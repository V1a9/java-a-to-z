package com.vgoryashko.testexercise.models;

import java.util.List;

/**
 * Class that defines User model.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/30/18
 */
public class User implements Entity {

    private long id;
    private String name;
    private String login;
    private String password;
    private long address;
    private long role;
    private List<Long> musics;

    public User() {
    }

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAddress() {
        return address;
    }

    public void setAddress(long address) {
        this.address = address;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    public List<Long> getMusics() {
        return musics;
    }

    public void setMusics(List<Long> musics) {
        this.musics = musics;
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

        if (!this.login.equals(user.login)) {
            return false;
        }
        return this.password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}

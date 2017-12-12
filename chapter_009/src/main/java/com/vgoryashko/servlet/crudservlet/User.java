package com.vgoryashko.servlet.crudservlet;

/**
 * Class that defines an User.
 *
 * @author Vlad Goryashko
 * @version 0.9
 * @since 12/12/17
 */
public class User {

    private String name;

    private String role;

    private String login;

    private String email;

    private String createDate;

    private String password;

    public User() {
    }

    public User(String name, String role, String login, String password, String email, String createDate) {
        this.name = name;
        this.role = role;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String eMail) {
        this.email = eMail;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return this.role;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getPassword() {
        return this.password;
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

        if (!name.equals(user.name)) {
            return false;
        }
        if (!login.equals(user.login)) {
            return false;
        }
        if (!email.equals(user.email)) {
            return false;
        }
        return createDate.equals(user.createDate);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + createDate.hashCode();
        return result;
    }
}

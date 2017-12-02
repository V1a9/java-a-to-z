package com.vgoryashko.servlet.crudservlet;

/**
 * Class that defines an User.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 12/02/17
 */
public class User {

    private String name;

    private String login;

    private String eMail;

    private String createDate;

    public User() {
    }

    public User(String name, String login, String eMail, String createDate) {
        this.name = name;
        this.login = login;
        this.eMail = eMail;
        this.createDate = createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String eMail) {
        this.eMail = eMail;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return eMail;
    }

    public String getCreateDate() {
        return createDate;
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
        if (!eMail.equals(user.eMail)) {
            return false;
        }
        return createDate.equals(user.createDate);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + eMail.hashCode();
        result = 31 * result + createDate.hashCode();
        return result;
    }
}

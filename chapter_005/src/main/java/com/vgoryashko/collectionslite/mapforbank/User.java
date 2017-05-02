package com.vgoryashko.collectionslite.mapforbank;

/**
 * Class that defines User parameters for a bank's customer.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 4/25/17
 */
public class User {

    /**
     * Variable that stores a name of an User.
     */
    private String name;

    /**
     * Variable that stores a passport data of an User.
     */
    private String passport;

    /**
     * Constructor for the class.
     *
     * @param name a name of an User
     * @param passport a passport data of an User
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (this.name != null ? !this.name.equals(user.name) : user.name != null) {
            return false;
        } else {

            return this.passport != null ? this.passport.equals(user.passport) : user.passport == null;
        }

    }

    @Override
    public int hashCode() {
        int result = this.name != null ? this.name.hashCode() : 0;
        result = 31 * result + (this.passport != null ? this.passport.hashCode() : 0);
        return result;
    }
}

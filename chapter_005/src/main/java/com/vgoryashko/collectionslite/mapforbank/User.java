package com.vgoryashko.collectionslite.mapforbank;

/**
 * Class that defines User parameters for a bank's customer.
 *
 * @author Vlad Goryashko
 * @version 0.2
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

    /**
     * Method that overrides hashcode().
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 85;
        return hash + this.name.hashCode() * 23 + this.passport.hashCode() / 18;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     */
    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }
}

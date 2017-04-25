package com.vgoryashko.collectionslite.mapforbank;

/**
 * Class that defines User parameters for a bank's customer.
 *
 * @author Vlad Goryashko
 * @version 0.1
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

}

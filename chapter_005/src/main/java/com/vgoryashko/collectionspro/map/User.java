package com.vgoryashko.collectionspro.map;

import java.util.Calendar;

/**
 * Class that implements User class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 13.06.2017
 */

public class User {

    /**
     * Variable that stores user name.
     */
    private String name;

    /**
     * Variable that stores qty of user's children.
     */
    private int children;

    /**
     * Variable that stores user's day of birth.
     */
    private Calendar birth;

    /**
     * Constructor for the class.
     *
     * @param newName name of an User
     * @param qty of children
     * @param dayOfBirth user's day of birth
     */
    public User(String newName, int qty, Calendar dayOfBirth) {
        this.name = newName;
        this.children = qty;
        this.birth = dayOfBirth;
    }
}

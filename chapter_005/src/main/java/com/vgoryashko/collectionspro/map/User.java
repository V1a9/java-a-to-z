package com.vgoryashko.collectionspro.map;

import java.util.Calendar;

/**
 * Class that implements User class.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 15.06.2017
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

    /**
     * Method that calculates hash code of an object.
     *
     * @return {@code int}
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        result = 31 * result + birth.get(Calendar.YEAR);
        result = 31 * result + birth.get(Calendar.MONTH);
        result = 31 * result + birth.get(Calendar.DAY_OF_MONTH);
        return result;
    }
}

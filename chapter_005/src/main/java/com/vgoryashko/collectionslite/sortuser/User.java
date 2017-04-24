package com.vgoryashko.collectionslite.sortuser;

/**
 * Class that defines an User.
 * <p>
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/24/17
 */
public class User implements Comparable<User> {

    /**
     * Variable that stores a name of an User.
     */
    private String name;

    /**
     * Variable that stores an age of an User.
     */
    private int age;

    /**
     * Constructor for the class.
     * <p>
     * @param name a name of an User.
     * @param age an age of an User.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Method that compares Users based on the values of the field age.
     * <p>
     * @param user an User to be compared.
     * @return 1, 0 or -1.
     */
    @Override
    public int compareTo(User user) {

        return (this.age > user.age ? 1 : (this.age == user.age ? 0 : -1));

    }
}

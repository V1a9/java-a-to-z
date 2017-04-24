package com.vgoryashko.collectionslite.sortuser;

/**
 * Class that defines an User.
 * <p>
 * @author Vlad Goryashko
 * @version 0.2
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

    /**
     * Getter for the field name.
     *
     * @return name of an User.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = 72;
        int ageHash = this.age * 13;
        return result + ageHash;

    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the ob
     */
    @Override
    public boolean equals(Object obj) {
        return (this.hashCode() == obj.hashCode());
    }
}

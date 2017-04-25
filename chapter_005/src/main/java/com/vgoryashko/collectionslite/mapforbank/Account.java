package com.vgoryashko.collectionslite.mapforbank;

/**
 * Class that defines a bank Account for an Users.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/25/17
 */
public class Account {

    /**
     * Variable that stores value of money on an account.
     */
    private double value;

    /**
     * Variable that stores an account requisites.
     */
    private int requisites;

    /**
     * Constructor for the class.
     *
     * @param value value of money
     * @param requisites requisites of an Account
     */
    public Account(double value, int requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Getter for the field value.
     *
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * Setter for the field value.
     *
     * @param value value of money on an account
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Method that overrides hashcode().
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 12;
        return hash + (int) this.value * 25 + this.requisites / 3;
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

package com.vgoryashko.collectionslite.mapforbank;

/**
 * Class that defines a bank Account for an Users.
 *
 * @author Vlad Goryashko
 * @version 0.4
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
     * Method that overrides method equals.
     *
     * @param o object to be compared
     * @return true if objects are equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        if (Double.compare(account.value, this.value) != 0) {
            return false;
        } else {
            return this.requisites == account.requisites;
        }

    }

    /**
     * Method that overrides a hashCode() method.
     *
     * @return int a hash value
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(this.value);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + this.requisites;
        return result;
    }
}

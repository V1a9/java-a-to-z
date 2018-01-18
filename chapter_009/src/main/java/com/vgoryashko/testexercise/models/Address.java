package com.vgoryashko.testexercise.models;

import java.util.Arrays;

/**
 * Class that defines Address model used in the application.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 1/15/18
 */
public class Address {

    private String[] address;

    public Address(String[] address) {
        this.address = address;
    }

    public String[] getAddress() {
        return this.address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }

        Address _address = (Address) o;

        for (int i = 0; i < this.address.length; i++) {
            if (!this.address[i].equals(_address.address[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.address);
    }
}

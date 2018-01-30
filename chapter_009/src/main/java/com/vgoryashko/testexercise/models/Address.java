package com.vgoryashko.testexercise.models;

/**
 * Class that defines Address model used in the application.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/30/18
 */
public class Address implements Entity {

    private long id;
    private String address;

    public Address() {
    }

    public Address(long id, String address) {
        this.id = id;
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }

        Address address1 = (Address) o;

        return address.equals(address1.address);
    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }
}

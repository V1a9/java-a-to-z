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

    private long id;
    private String country;
    private String city;
    private String street;
    private String apartment;

    public Address() {
    }

    public Address(String country, String city, String street, String apartment) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.apartment = apartment;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
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

        Address address = (Address) o;

        if (!country.equals(address.country)) {
            return false;
        }
        if (!city.equals(address.city)) {
            return false;
        }
        if (!street.equals(address.street)) {
            return false;
        }
        return apartment.equals(address.apartment);
    }

    @Override
    public int hashCode() {
        int result = country.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + apartment.hashCode();
        return result;
    }
}

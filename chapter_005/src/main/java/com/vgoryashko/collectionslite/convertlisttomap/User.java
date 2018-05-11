package com.vgoryashko.collectionslite.convertlisttomap;

/**
 * Class that defines an user.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/23/17
 */
public class User {

    /**
     * Variable that stores a name of an user.
     */
    private String name;

    /**
     * variable that stores an user id.
     */
    private int id;

    /**
     * Variable that stores a city name of an user.
     */
    private String city;

    /**
     * Constructor for the class.
     *
     * @param aName a name of an user.
     * @param aId an user id.
     * @param aCity a city name of an user.
     */
    public User(String aName, int aId, String aCity) {
        this.name = aName;
        this.id = aId;
        this.city = aCity;
    }

    /**
     * Getter for the field name.
     * @return <>String</>
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the field id.
     * @return integer
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the field city.
     * @return String
     */
    public String getCity() {
        return city;
    }
}

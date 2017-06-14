package com.vgoryashko.collectionspro.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that implements Map with users.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 14.06.2017
 *
 * @param <T> type of key parameter to be stored in Map.
 * @param <V> type of value parameter to be associated with key in Map.
 */

public class MapOfUsers<T, V> {

    /**
     * Instance of Map.
     */
    private Map<T, V> map = new HashMap<>();

    /**
     * Method that adds elements into collection.
     * @param user key
     * @param obj value
     */
    public void addUsers(T user, V obj) {

        map.put(user, obj);

    }

    /**
     * Method that returns instance of Map.
     * @return Map
     */
    public Map<T, V> getMap() {
        return this.map;
    }

}

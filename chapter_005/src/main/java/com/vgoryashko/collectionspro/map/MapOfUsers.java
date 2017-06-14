package com.vgoryashko.collectionspro.map;

import java.util.*;

/**
 * Class that implements Map with users.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 13.06.2017
 */

public class MapOfUsers<T, V> {

    private Map<T, V> map = new HashMap<>();

    public void addUsers(T user, V obj) {

        map.put(user, obj);

    }

    public Map<T, V> getMap() {
        return this.map;
    }

}

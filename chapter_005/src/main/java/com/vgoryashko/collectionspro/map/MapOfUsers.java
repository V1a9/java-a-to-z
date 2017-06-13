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

    Map<T, V> set = new HashMap<>();

    Calendar calendar = Calendar.getInstance().set(1982, 12, 11);

    User user1 = new User("Dan", 2, calendar);

}

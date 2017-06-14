package com.vgoryashko.collectionspro.map;

import org.junit.Test;

import java.util.Calendar;

/**
 * Class that tests collection Map.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 6/14/17
 */
public class MapOfUsersTest {

    /**
     * Test of adding Users into Map.
     */
    @Test
    public void whenTwoUsersWithEqualsFieldsAddedThenTwoUsersWillBeInTheMap() {

        MapOfUsers<User, Object> mapOfUsers = new MapOfUsers<>();

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(1982, 2, 10);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1982, 2, 10);

        mapOfUsers.addUsers(new User("Tom", 1, calendar1), new Object());
        mapOfUsers.addUsers(new User("Tom", 1, calendar2), new Object());

        System.out.println(mapOfUsers.getMap());
    }
}
package com.vgoryashko.collectionspro.map;

import org.junit.Test;

import java.util.Calendar;

/**
 * Class that tests collection Map.
 *
 * @author Vlad Goryashko
 * @version 0.2
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
        User user1 = new User("Dan", 2, calendar1);

        Calendar calendar2 = Calendar.getInstance();
        User user2 = new User("Dan", 2, calendar2);

        mapOfUsers.addUsers(user1, new Object());
        mapOfUsers.addUsers(user2, new Object());

        System.out.println(mapOfUsers.getMap());

        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());

    }

}
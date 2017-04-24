package com.vgoryashko.collectionslite.sortuser;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 * Class that tests SortUser application.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/24/17
 */
public class SortUserTest {

    /**
     * Method that tests sorting of Users.
     */
    @Test
    public void whenSortInvokedThenListUsersIsSortedInAscendingOrder() {

        SortUser sortUser = new SortUser();

        TreeSet<User> expectedSet = new TreeSet<>();
        expectedSet.add(new User("Tom", 2));
        expectedSet.add(new User("Jessica", 5));
        expectedSet.add(new User("Ben", 10));

        List<User> userList = Arrays.asList(new User("Ben", 10), new User("Jessica", 5), new User("Tom", 2));
        assertEquals(expectedSet, sortUser.sort(userList));

    }

}
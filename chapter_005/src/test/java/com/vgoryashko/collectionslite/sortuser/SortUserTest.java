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
 * @version 0.3
 * @since 4/25/17
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

    /**
     * Method that tests sorting of Users by hash-codes.
     */
    @Test
    public void whenSortHashInvokedThenListUsersIsSortedByHash() {

        SortUser sortUser = new SortUser();
        List<User> userList = Arrays.asList(new User("Ben", 10), new User("Jessica", 3), new User("Tom", 2));
        List<User> userListExpected = Arrays.asList(new User("Tom", 2), new User("Jessica", 3), new User("Ben", 10));
        assertEquals(sortUser.sortHash(userList), userListExpected);
    }

    /**
     * Method that tests sorting of Users by name length.
     */
    @Test
    public void whenSortHashInvokedThenListUsersIsSortedByNameLength() {

        SortUser sortUser = new SortUser();
        List<User> userList = Arrays.asList(new User("Ben", 10), new User("Jessica", 3), new User("Tom", 2));
        List<User> userListExpected = Arrays.asList(new User("Ben", 10), new User("Tony", 2), new User("Jessica", 3));
        assertEquals(sortUser.sortLength(userList), userListExpected);

    }

}
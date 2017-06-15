package com.vgoryashko.collectionspro.store;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Class that tests Store application.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 15.06.2017
 */

public class UserStoreTest {

    /**
     * Variable that referring to instance of User.
     */
    private User user1;

    /**
     * Variable that referring to instance of User.
     */
    private User user2;

    /**
     * Variable that referring to instance of User.
     */
    private User user3;

    /**
     * Variable that referring to an instance of the Store object.
     */
    private Store<User> userStore;

    /**
     * Method that initialize test environments.
     */
    @Before
    public void init() {

        userStore = new UserStore<>();

        user1 = new User();
        user2 = new User();
        user3 = new User();

        user1.setId("1");
        user2.setId("2");
        user3.setId("3");
    }

    /**
     * Method that tests adding of User to collection.
     *
     * @throws Exception Exception
     */
    @Test
    public void whnAddInvokedThenUserAdded() throws Exception {

        userStore.add(user1);
        assertThat(userStore.get("1"), is(user1));

    }

    /**
     * Method that tests update of an User.
     *
     * @throws Exception Exception
     */
    @Test
    public void whenUpdateInvokedThenElementUpdated() throws Exception {

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);

        User user4 = new User();
        user4.setId("4");

        userStore.update("3", user4);
        assertThat(userStore.get("4"), is(user4));

    }

    /**
     * Method that tests removing of an User.
     *
     * @throws Exception Exception
     */
    @Test
    public void whenRemoveInvokedThenUserRemoved() throws Exception {

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);

        userStore.remove("2");
        assertNull(userStore.get("2"));

    }

}
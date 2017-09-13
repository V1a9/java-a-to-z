package com.vgoryashko.multithreading.monitore;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class that tests UserStorage application.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 9/13/17
 */
public class UserStoreTest {

    /**
     * Instance variable for UserStore.
     */
    private UserStore userStore;

    /**
     * Iterator over UserStore elements.
     */
    private Iterator iterator;

    /**
     * Method that setups environments.
     */
    @Before
    public void setUp() {

        userStore = new UserStore();
        userStore.add(1, 100);
        userStore.add(2, 200);
        userStore.add(3, 300);

    }

    /**
     * Method that tests add().
     * @throws Exception Exception
     */
    @Test
    public void whenAddInvokedThenNewUserIsAdded() throws Exception {

        iterator = userStore.iterator();
        Integer[] userData;
        Integer[] expected = new Integer[]{1, 2, 3};
        int index = 0;

        while (iterator.hasNext()) {

            userData = (Integer[]) iterator.next();
            assertThat(userData[0], is(expected[index]));
            index++;

        }


    }

    /**
     * Method that tests update().
     * @throws Exception Exception
     */
    @Test
    public void whenUpdateInvokedThenUserUpdated() throws Exception {

        Integer[] result;
        userStore.update(3, 4, 150);
        iterator = userStore.iterator();

        for (int i = 0; i < 3; i++) {
            iterator.hasNext();
            result = (Integer[]) iterator.next();
            if (i == 2) {
                assertThat(result[0], is(4));

            }

        }

    }

    /**
     * Method that tests delete().
     * @throws Exception Exception
     */
    @Test
    public void whenDeleteInvokedThenUserDeleted() throws Exception {

        userStore.delete(2);
        assertThat(userStore.getUserList().size(), is(2));

    }

    /**
     * Method that tests transfer().
     * @throws Exception Exception
     */
    @Test
    public void whenTransferInvokedThenMoneyTransferredBetweenUsers() throws Exception {

        userStore.transfer(3, 1, 100);
        iterator = userStore.iterator();
        Integer[] result = (Integer[]) iterator.next();

        assertThat(result[1], is(200));

    }

}
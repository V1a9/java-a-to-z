package com.vgoryashko.collectionspro.store;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Class that test RoleStore collection.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 13.06.2017
 */

public class RoleStoreTest {

    /**
     * Variable that referring to instance of Role.
     */
    private Role role1;

    /**
     * Variable that referring to instance of Role.
     */
    private Role role2;

    /**
     * Variable that referring to instance of Role.
     */
    private Role role3;

    /**
     * Variable that referring to an instance of the Store object.
     */
    private Store<Role> roleStore;

    /**
     * Method that initialize test environments.
     */
    @Before
    public void init() {

        roleStore = new RoleStore<>();

        role1 = new Role();
        role2 = new Role();
        role3 = new Role();

        role1.setId("1");
        role1.setId("2");
        role1.setId("3");
    }

    /**
     * Method that tests adding of Role to collection.
     *
     * @throws Exception Exception
     */
    @Test
    public void whnAddInvokedThenRoleAdded() throws Exception {

        roleStore.add(role1);
        assertThat(roleStore.get(0), is(role1));

    }

    /**
     * Method that tests update of an Role.
     *
     * @throws Exception Exception
     */
    @Test
    public void whenUpdateInvokedThenElementUpdated() throws Exception {

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);

        Role role4 = new Role();
        role4.setId("4");

        roleStore.update(3, role4);
        assertThat(roleStore.get(3), is(role4));

    }

    /**
     * Method that tests removing of an Role.
     *
     * @throws Exception Exception
     */
    @Test
    public void whenRemoveInvokedThenRoleRemoved() throws Exception {

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);

        roleStore.remove(2);
        assertNull(roleStore.get(2));

    }

}
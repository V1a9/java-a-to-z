package com.vgoryashko.collectionslite.mapforbank;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class that tests implementation of classes the realize Bank logic.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 4/25/17
 */
public class BankTest {

    /**
     * Variable that refers to Bank instance.
     */
    private Bank bank;

    /**
     * Variable that referring to an instance of a User.
     */
    private User user1;

    /**
     * Variable that referring to an instance of a User.
     */
    private User user2;

    /**
     * Variable that referring to an Account instance.
     */
    private Account account1;

    /**
     * Variable that referring to an Account instance.
     */
    private Account account2;

    /**
     * Method that setups test environments.
     */
    @Before
    public void init() {
        bank = new Bank();
        user1 = new User("Ben", "MF123");
        account1 = new Account(100.00, 1234);
        account2 = new Account(200.00, 234);
        bank.addUser(user1);
        bank.addAccountToUser(user1, account1);
        bank.addAccountToUser(user1, account2);
    }

    /**
     * Method that tests adding of an User.
     */
    @Test
    public void addUser() {
        assertTrue(bank.getUserMap().containsKey(user1));
    }

    /**
     * Method that test deleting of an User from a Map.
     */
    @Test
    public void deleteUser() {
        bank.deleteUser(user1);
        assertTrue(!bank.getUserMap().containsKey(user1));
    }

    /**
     * Method that tests adding of an Account to a User.
     */
    @Test
    public void addAccountToUser() {
        assertTrue(bank.getUserMap().get(user1).contains(account1));
        assertTrue(bank.getUserMap().get(user1).contains(account2));
    }

    /**
     * Method that tests deletion of an Account.
     */
    @Test
    public void deleteAccountFromUser() {
        bank.deleteAccountFromUser(user1, account1);
        assertTrue(!bank.getUserMap().get(user1).contains(account1));
    }

    /**
     * Method that tests retrieving of an user's accounts.
     */
    @Test
    public void getUserAccounts() {
        List<Account> expectedList = Arrays.asList(account1, account2);
        assertEquals(expectedList, bank.getUserAccounts(user1));
    }

    /**
     * Method that tests transferring of money between users.
     */
    @Test
    public void transferMoney() {
        user2 = new User("Tom", "ME1234");
        Account account3 = new Account(100.00, 4321);
        Account account4 = new Account(400.00, 432);
        bank.addUser(user2);
        bank.addAccountToUser(user2, account3);
        bank.addAccountToUser(user2, account4);

        bank.transferMoney(user2, account4, user1, account1, 50.00);

        assertTrue(bank.getUserMap().get(user1).get(0).getValue() == 150.00);

        assertFalse(bank.transferMoney(user2, account4, user1, account1, 500.00));
    }

    /**
     * Method that tests User equals method.
     */
    @Test
    public void whenEqualsInvokedThenCorrectResultReceived() {

        User u1 = new User("A", "B");
        User u2 = new User("A", "B");

        assertTrue(u1.equals(u2));

        u2 = null;

        assertFalse(u1.equals(u2));

        u2 = new User("C", "B");

        assertFalse(u1.equals(u2));

    }

}
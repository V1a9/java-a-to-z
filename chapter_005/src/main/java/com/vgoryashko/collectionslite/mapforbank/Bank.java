package com.vgoryashko.collectionslite.mapforbank;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that implements a Bank logic.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/25/17
 */
public class Bank {

    /**
     * Variable that referring to a Map collection of Users.
     */
    private Map<User, List<Account>> userMap;

    /**
     * Constructor for the class.
     */
    public Bank() {
        this.userMap = new LinkedHashMap<>();
    }

    /**
     * Method that adds an User.
     *
     * @param user user to be added
     */
    public void addUser(User user) {
        this.userMap.put(user, new ArrayList<>());
    }

    /**
     * Method that removes an User.
     *
     * @param user user to be deleted
     */
    public void deleteUser(User user) {
        if (this.userMap.containsKey(user)) {
            this.userMap.remove(user);
        }
    }

    /**
     * Method that adds an Account to an User.
     *
     * @param user user whom account will be added to
     * @param account account to be added
     */
    public void addAccountToUser(User user, Account account) {
        if (this.userMap.containsKey(user)) {
            this.userMap.get(user).add(account);
        }
    }

    /**
     * Method that deletes an Account from an User.
     *
     * @param user user whom account will be deleted from
     * @param account account to be deleted
     */
    public void deleteAccountFromUser(User user, Account account) {
        if (this.userMap.containsKey(user)) {
            this.userMap.get(user).remove(account);
        }
    }

    /**
     * Method that lists all accounts of an User.
     *
     * @param user user those accounts will be listed
     * @return List of Account
     */
    public List<Account> getUserAccounts(User user) {
        List<Account> accounts = new ArrayList<>();
        if (this.userMap.containsKey(user)) {
            accounts = this.userMap.get(user);
        }
        return accounts;
    }

    /**
     * Method that implements transferring of money from an account of one User to an account of another User.
     *
     * @param srcUser source User
     * @param srcAccount source Account
     * @param dstUser dest User
     * @param dstAccount dest Account
     * @param amount value to be transferred
     * @return true if transaction was successful - false otherwise
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {

        boolean result = false;

        if (this.userMap.get(srcUser).contains(srcAccount)) {

            for (Account account : this.userMap.get(srcUser)) {
                if (account.equals(srcAccount)) {
                    if (account.getValue() >= amount) {
                        for (Account account1 : this.userMap.get(dstUser)) {
                            if (account1.equals(dstAccount)) {
                                account.setValue(account.getValue() - amount);
                                account1.setValue(account1.getValue() + amount);
                                result = true;
                            }
                        }
                    } else {
                        result = false;
                    }
                }
            }
        } else {

            result = false;

        }
        return result;
    }

    /**
     * Getter for the field userMap.
     * @return Map
     */
    public Map<User, List<Account>> getUserMap() {
        return userMap;
    }
}

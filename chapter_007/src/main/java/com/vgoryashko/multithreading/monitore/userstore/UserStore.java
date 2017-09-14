package com.vgoryashko.multithreading.monitore.userstore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class that implements UserStorage data structure.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 9/13/17
 */

@ThreadSafe
public class UserStore {

    /**
     * Variable that refers to a collection structure.
     */
    @GuardedBy("this") private final List<User> userList;

    /**
     * Constructor for the class.
     */
    UserStore() {

        this.userList = new ArrayList<>();

    }

    /**
     * Class that defines an User structure.
     */
    private class User {

        /**
         * Variable that stores an unique User's ID.
         */
        private int id;

        /**
         * Variable that stores an amount of money for an User.
         */
        private int amount;

        /**
         * Constructor for the class.
         *
         * @param id unique number
         * @param amount balance
         */
        User(int id, int amount) {

            this.id = id;
            this.amount = amount;

        }

        /**
         * Getter for id.
         * @return int
         */
        public int getId() {
            return this.id;
        }

        /**
         * Getter for amount.
         * @return int
         */
        public int getAmount() {
            return this.amount;
        }
    }

    /**
     * Method that adds an User to the storage.
     *
     * @param id user's id
     * @param amount balance
     */
    public synchronized void add(int id, int amount) {

        this.userList.add(new User(id, amount));

    }

    /**
     * Method that updates an User.
     *
     * @param idOld id of current User
     * @param idNew id of new User
     * @param amount balance
     */
    public synchronized void update(int idOld, int idNew, int amount) {

        int index = 0;

        for (User user : this.userList) {

            if (user.id == idOld) {

                index = this.userList.indexOf(user);
                break;

            }

        }

        this.userList.remove(index);
        this.userList.add(index, new User(idNew, amount));

    }

    /**
     * Method that deletes an User based on id.
     *
     * @param id unique id
     */
    public synchronized void delete(int id) {

        int index = 0;

        for (User user : this.userList) {

            if (user.id == id) {
                index = this.userList.indexOf(user);

            }

        }

        this.userList.remove(index);
    }

    /**
     * Method that transfers money between two users.
     *
     * @param fromId id od an User who sends
     * @param toId id od an User who receives
     * @param amount to be sent
     */
    public synchronized void transfer(int fromId, int toId, int amount) {

        User fromUser = null;
        User toUser = null;

        for (User user : this.userList) {

            if (user.id == fromId) {

                fromUser = user;

            } else if (user.id == toId) {

                toUser = user;

            }

        }

        if (toUser != null && fromUser != null) {

            toUser.amount = toUser.amount + amount;
            fromUser.amount = fromUser.amount - amount;

        }

    }

    /**
     * Method that allows go through all elements of data base.
     *
     * @return Integer[] array of id and amount for a particular user
     */
    public synchronized Iterator<Integer[]> iterator() {

        return new Iterator<Integer[]>() {

            private int index = 0;
            private int position = index;
            private boolean result;

            @Override
            public boolean hasNext() {

                position = index;

                if (position < userList.size()) {

                    result = true;

                } else {

                    result = false;

                }

                return result;
            }

            @Override
            public Integer[] next() {

                index++;
                return new Integer[]{userList.get(position).getId(), userList.get(position).getAmount()};

            }
        };

    }

    /**
     * Getter for the userList.
     *
     * @return ArrayList<User>
     */
    public List<User> getUserList() {
        return this.userList;
    }
}

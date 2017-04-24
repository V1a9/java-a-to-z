package com.vgoryashko.collectionslite.sortuser;

import java.util.List;
import java.util.Set;
import java.util.Collections;
import java.util.TreeSet;
import java.util.Comparator;

/**
 * Class that implements sorting of Users.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/24/17
 */
public class SortUser {

    /**
     * Method that sorts Users by age in the ascending order.
     * <p>
     * @param userList list of Users to be sorted.
     * @return TreeSet of sorted Users.
     */
    public Set<User> sort(List<User> userList) {

        Collections.sort(userList);

        return new TreeSet<>(userList);
    }

    /**
     * Method that sorts Users by hash-code.
     *
     * @param userList list of Users to be sorted.
     * @return List<User>
     */
    public List<User> sortHash(List<User> userList) {

        Comparator<User> comparatorByHash = new Comparator<User>() {

            @Override
            public int compare(User o1, User o2) {
                return (o1.hashCode() > o2.hashCode() ? 1 : ((o1.hashCode() == o2.hashCode() ? 0 : -1)));
            }
        };

        Collections.sort(userList, comparatorByHash);

        return userList;
    }

    /**
     * Method that sorts Users by name length.
     *
     * @param userList list of Users to be sorted.
     * @return List<User>
     */
    public List<User> sortLength(List<User> userList) {

        Comparator<User> comparatorByLength = new Comparator<User>() {

            @Override
            public int compare(User o1, User o2) {
                return (o1.getName().length() > o2.getName().length() ? 1 : ((o1.getName().length() == o2.getName().length() ? 0 : -1)));
            }
        };

        Collections.sort(userList, comparatorByLength);

        return userList;
    }

}

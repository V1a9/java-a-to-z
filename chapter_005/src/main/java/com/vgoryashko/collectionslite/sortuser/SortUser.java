package com.vgoryashko.collectionslite.sortuser;

import java.util.Collections;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;

/**
 * Class that implements sorting of Users.
 *
 * @author Vlad Goryashko
 * @version 0.1
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
}

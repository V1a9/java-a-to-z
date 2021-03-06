package com.vgoryashko.collectionslite.convertlisttomap;

import java.util.HashMap;
import java.util.List;

/**
 * Class that converts List<User> to a Map.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 4/23/17
 */
public class UserConvert {

    /**
     * Method that converts List<User> to a Map.
     *
     * @param aUserList List to be converted
     * @return <code>HashMap<Integer, User></code>
     */
    public HashMap<Integer, User> process(List<User> aUserList) {

        HashMap<Integer, User> map = new HashMap<>();

        for (User user : aUserList) {
            map.put(user.getId(), user);
        }

        return map;
    }
}

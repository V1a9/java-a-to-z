package com.vgoryashko.collectionslite.convertlisttomap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Class that tests implementation of the UserConvert.class.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/23/17
 */
public class UserConvertTest {

    /**
     * Method that tests process() method.
     */
    @Test
    public void whenProcessInvokedThenListConvertedToMap() {

        Object[] objects = new Object[3];

        objects[0] = new User("user0", 0, "New York");
        objects[1] = new User("user1", 1, "Vancouver");
        objects[2] = new User("user2", 2, "California");

        List<User> userList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            userList.add((User) objects[i]);
        }

        HashMap<Integer, User> resultMap = new UserConvert().process(userList);

        assertEquals(resultMap.get(1), objects[1]);
        assertEquals(resultMap.get(0), objects[0]);

    }

}
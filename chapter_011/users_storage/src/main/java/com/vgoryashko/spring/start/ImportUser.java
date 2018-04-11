package com.vgoryashko.spring.start;

import com.vgoryashko.spring.dao.UserStorage;
import com.vgoryashko.spring.models.User;

/**
 * Class that implements main logic of the application.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/9/18
 */
public class ImportUser {

    private final UserStorage storage;

    public ImportUser(UserStorage storage) {
        this.storage = storage;
    }

    public void addUser(User user) {
        this.storage.add(user);
    }
}

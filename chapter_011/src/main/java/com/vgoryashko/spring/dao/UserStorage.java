package com.vgoryashko.spring.dao;

import com.vgoryashko.spring.models.User;
import org.springframework.stereotype.Component;

/**
 * Class that implements UserStorage.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/30/18
 */
@Component
public class UserStorage {

    private final Storage storage;

    public UserStorage(Storage storage) {
        this.storage = storage;
    }

    public void add(User user) {
        this.storage.add(user);
    }

    public Storage getStorage() {
        return storage;
    }
}

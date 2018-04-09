package com.vgoryashko.spring.dao;

import com.vgoryashko.spring.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements sotrage of Users' in the memory.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 4/09/18
 */
@Component
public class MemoryStorage implements Storage {

    private final List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        this.users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
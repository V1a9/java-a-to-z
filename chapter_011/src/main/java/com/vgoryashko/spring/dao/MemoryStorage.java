package com.vgoryashko.spring.dao;

import com.vgoryashko.spring.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Class that implements sotrage of Users' in the memory.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/30/18
 */
@Component
public class MemoryStorage implements Storage {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryStorage.class);

    @Override
    public void add(User user) {
        System.out.println("User added to memory...");
    }
}

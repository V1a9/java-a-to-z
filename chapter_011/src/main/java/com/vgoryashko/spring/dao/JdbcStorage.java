package com.vgoryashko.spring.dao;

import com.vgoryashko.spring.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that implements Users' storage in the DB.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/30/18
 */
public class JdbcStorage implements Storage {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcStorage.class);

    @Override
    public void add(User user) {
        System.out.println("User added to DB...");
    }
}

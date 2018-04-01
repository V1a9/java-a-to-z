package com.vgoryashko.spring.dao;

import com.vgoryashko.spring.models.User;

/**
 * Interface that defines contract for Storage class.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/30/18
 */
public interface Storage {

    void add(User user);

}

package com.vgoryashko.testexercise.repositories;

import com.vgoryashko.testexercise.models.User;

import java.util.List;

/**
 * Class that implement Repository for Role.
 *
 * @author Vlad Goryashko
 * @version 0.5
 * @since 2/16/18
 */
public interface RoleRepository extends Repository<User> {

    /**
     * Method that retrieves all linked entities with a given Role.
     * @param id of a Role
     * @return List of Entities
     */
    List<User> get(long id);
}

package com.vgoryashko.testexercise.repositories;

import com.vgoryashko.testexercise.models.Role;

import java.util.List;

/**
 * Class that implement Repository for Role.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 2/08/18
 */
public class RoleRepository implements Repository<Role> {

    public RoleRepository() {
    }

    /**
     * Method that retrieves all linked entities wit a given Role.
     * @param id of a Role
     * @return List of Entities
     */
    @Override
    public List<Role> get(long id) {
        return null;
    }
}

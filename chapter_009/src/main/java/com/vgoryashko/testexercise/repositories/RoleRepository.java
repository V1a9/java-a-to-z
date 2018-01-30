package com.vgoryashko.testexercise.repositories;

import com.vgoryashko.testexercise.models.Entity;
import com.vgoryashko.testexercise.models.Role;

import java.util.List;

/**
 * Class that implement Repository for Role.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/30/18
 */
public class RoleRepository implements Repository<Role, Entity> {

    public RoleRepository() {
    }

    @Override
    public List<Entity> get(Role role) {
        return null;
    }
}

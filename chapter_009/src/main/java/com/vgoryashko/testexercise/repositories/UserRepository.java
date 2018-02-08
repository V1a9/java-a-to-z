package com.vgoryashko.testexercise.repositories;

import com.vgoryashko.testexercise.models.Entity;
import com.vgoryashko.testexercise.models.User;

import java.util.List;

/**
 * Class that implements Repository for User.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 2/08/18
 */
public interface UserRepository<E  extends Entity> extends Repository<E> {

    /**
     * Method that create an User with all linked entities.
     * @param entities
     * @return true or false
     */
    boolean add(List<E> entities);

    /**
     * Method that retrieves all linked entities for a given Entity.
     *
     * @param id of an User
     * @return List of Entities
     */
    @Override
    List<E> get(long id);

    /**
     * Method that searches an User based on a given criteria (Role, Address or Music type).
     * @param entity
     * @return
     */
    List<User> find(Entity entity);

}

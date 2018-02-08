package com.vgoryashko.testexercise.repositories;

import com.vgoryashko.testexercise.models.Entity;

import java.util.List;

/**
 * Interface that defines repositories entity.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 2/08/18
 */
public interface Repository<E extends Entity> {

    /**
     * Method that retrieves all linked entities for a given Entity.
     * @param id of an Entity
     * @return List of Entities
     */
    List<E> get(long id);

}

package com.vgoryashko.testexercise.dao;

import java.util.List;

/**
 * Interface that defines Data Access Object used in the application.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/21/18
 */
public interface DAO<T> {

    boolean create(T t);
    T read(long id);
    List<T> readAll();
    boolean update(T t, long id);
    boolean delete(long id);

}

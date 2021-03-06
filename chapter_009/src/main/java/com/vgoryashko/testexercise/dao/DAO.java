package com.vgoryashko.testexercise.dao;

import java.util.List;

/**
 * Interface that defines Data Access Object used in the application.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 2/08/18
 */
public interface DAO<T> {

    long create(T t);
    T read(long id);
    List<T> readAll();
    boolean update(T t, long id);
    boolean delete(long id);
    long exists(T t);

}

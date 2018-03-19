package com.vgoryashko.hibernate.carsstore.dao;

import java.util.List;

/**
 * Interface that defines DAO contract.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/2/18
 */
public interface DAO<T> {
    long create(T t);
    T read(long id);
    List readAll();
    boolean update(T t);
    boolean delete(T t);
}

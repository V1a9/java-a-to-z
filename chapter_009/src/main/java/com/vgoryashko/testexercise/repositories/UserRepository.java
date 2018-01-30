package com.vgoryashko.testexercise.repositories;

/**
 * Class that implements Repository for User.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/30/18
 */
public interface UserRepository<T, E> extends Repository<T, E> {

    boolean add(T t);

    T find(String criteria);

}

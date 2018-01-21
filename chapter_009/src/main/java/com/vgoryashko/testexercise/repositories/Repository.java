package com.vgoryashko.testexercise.repositories;

import java.util.List;

/**
 * Interface that defines repositories entity.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/21/18
 */
public interface Repository<T> {

    List<T> get();
    boolean add(T t);
    T read();

}

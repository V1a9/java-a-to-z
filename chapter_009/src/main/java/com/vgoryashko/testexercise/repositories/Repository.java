package com.vgoryashko.testexercise.repositories;

import java.util.List;

/**
 * Interface that defines repositories entity.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 1/30/18
 */
public interface Repository<T, E> {

    List<E> get(T t);

}

package com.vgoryashko.hibernate.carsstore.service;

/**
 * Class that implements exception that is thrown when a wrong table choosen in DAOManager.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/2/18
 */
public class WrongTableException extends RuntimeException {
    public WrongTableException(String message) {
        super(message);
    }
}

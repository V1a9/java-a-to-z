package com.vgoryashko.chess;

/**
 * Class that is used for validation of a destination cell (error if wrong cell was entered).
 * @author Vlad Goryashko
 * @since 07.12.2016
 * @version 0.1
 */
public class ImpossibleMoveException extends RuntimeException {

    /**
     * Constructor for the class.
     * @param message                               Error message
     */
    public ImpossibleMoveException(String message) {
        super(message);
    }
}

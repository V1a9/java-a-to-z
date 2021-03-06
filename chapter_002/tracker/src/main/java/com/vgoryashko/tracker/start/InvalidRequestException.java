package com.vgoryashko.tracker.start;

/**
 * Class that is used for validation of user input via console.
 * @author Vlad Goryashko
 * @since 05.12.2016
 * @version 1.0
 */
public class InvalidRequestException extends RuntimeException {

    /**
     * Constructor for the class.
     * @param message                                   Error message
     * @throws                                          RuntimeException if an invalid request name or id is entered.
     */
    public InvalidRequestException(String message) {
        super(message);
    }
}

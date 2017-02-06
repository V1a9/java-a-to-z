package com.vgoryashko.checkpalindrome;

/**
 * Class that defines exception which is thrown when wrong word was entered.
 *
 * @author Vlad Goryashko
 * @version 0.12
 * @since 06.02.2017
 */
public class InvalidWordException extends RuntimeException {

    /**
     * Constructor of the class.
     * @param message                   message that describes exception.
     */
    public InvalidWordException(String message) {
        super(message);
    }
}

package com.vgoryashko.checkpalindromev2;

/**
 * Class that defines exception which is thrown when a letter with wrong number of letters is entered.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/15/17
 */
public class InvalidNumberOfLettersException extends RuntimeException {

    /**
     * Constructor for the class.
     * @param message                                   Error message
     * @throws                                          RuntimeException if a word with number of letters not equal 5 was entered.
     */
    public InvalidNumberOfLettersException(String message) {
        super(message);
    }
}

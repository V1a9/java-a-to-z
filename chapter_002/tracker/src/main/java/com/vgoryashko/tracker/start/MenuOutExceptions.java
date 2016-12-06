package com.vgoryashko.tracker.start;

/**
 * Class that is used for validation of user input via console.
 * @author Vlad Goryashko
 * @since 03.12.2016
 * @version 1.0
 */
public class MenuOutExceptions extends RuntimeException {

    /**
     * Constructor for the class.
     * @param message                                   Error message
     * @throws                                          RuntimeException if an invalid option in a menu is chosen
     */
    public MenuOutExceptions(String message) {
        super(message);
    }
}

package com.vgoryashko.chess;

/**
 * Class that is used for reporting if a figure is not found).
 * @author Vlad Goryashko
 * @since 07.12.2016
 * @version 0.1
 */
public class FigureNotFoundException extends RuntimeException{
    /**
     * Constructor for the class.
     * @param message                               Error message
     */
    public FigureNotFoundException(String message) {
        super(message);
    }
}

package com.vgoryashko.chess.game;

/**
 * Class that is used for validation of a destination cell (error if a cell is occupied).
 * @author Vlad Goryashko
 * @since 07.12.2016
 * @version 0.1
 */
public class OccupiedWayException extends RuntimeException {
    /**
     * Constructor for the class.
     * @param message                               Error message
     */
    public OccupiedWayException(String message) {
        super(message);
    }
}

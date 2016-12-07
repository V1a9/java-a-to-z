package com.vgoryashko.chess;

/**
 * Class that define common members for chess figures.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 07/12/2016
 */
public abstract class Figure {
    /**
     * Variable that holds a cell position for a figure.
     */
    private final Cell position;

    /**
     * Constructor for the class.
     * @param aPosition                         initializing cell variable for a figure.
     */
    public Figure(Cell aPosition) {
        this.position = aPosition;
    }

    /**
     * Method that checks correctness of a figure movement.
     * @param dist                                  coordinates of a cell where a figure is going to be moved
     * @return Cell[]                               an array of cells that a figure has to pass if a final cell is correct
     * @throws ImpossibleMoveException              exception in case a wrong destination cell was entered
     */
    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        throw new ImpossibleMoveException("Wrong movement.");
    }
}
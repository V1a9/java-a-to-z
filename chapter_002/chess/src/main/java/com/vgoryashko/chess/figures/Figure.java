package com.vgoryashko.chess.figures;

import com.vgoryashko.chess.game.Cell;
import com.vgoryashko.chess.game.ImpossibleMoveException;

/**
 * Class that define common members for chess figures.
 * @author Vlad Goryashko
 * @version 0.2
 * @since 19/12/2016
 */
public abstract class Figure {
    /**
     * Method that checks correctness of a figure movement.
     * @param dest                                  coordinates of a cell where a figure is going to be moved
     * @return Cell[]                               an array of cells that a figure has to pass if a final cell is correct
     * @throws ImpossibleMoveException              exception in case a wrong destination cell was entered
     */
    public abstract Cell[] way(Cell dest) throws ImpossibleMoveException;

    /**
     * Get figure position.
     * @return                                      position
     */
    public abstract Cell getPosition();

    /**
     * Abstract method that is intended for creation of a new Figure.
     * @param dest                              destination cell
     * @return Figure
     */
    public abstract Figure clone(Cell dest);

    /**
     * Get a color of a figure.
     * @return boolean
     */
    public abstract boolean getColor();
}
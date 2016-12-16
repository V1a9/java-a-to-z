package com.vgoryashko.chess.figures;

import com.vgoryashko.chess.game.Board;
import com.vgoryashko.chess.game.Cell;
import com.vgoryashko.chess.game.ImpossibleMoveException;

/**
 * Class that define common members for chess figures.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 07/12/2016
 */
public abstract class Figure implements Cloneable{
    /**
     * Variable that holds a cell position for a figure.
     */
    protected final Cell position;

    /**
     * Variable that defines a color of a figure (true is white, false is black).
     */
    protected boolean color;
    /**
     * Constructor for the class.
     * @param aPosition                         initial cell coordinates
     * @param aColor                            defines cell's color (white or black)
     */
    public Figure(Cell aPosition, boolean aColor) {
        this.position = aPosition;
        this.color = aColor;
    }
    /**
     * Method that checks correctness of a figure movement.
     * @param dest                                  coordinates of a cell where a figure is going to be moved
     * @return Cell[]                               an array of cells that a figure has to pass if a final cell is correct
     */
    public abstract Cell[] way(Cell dest) throws ImpossibleMoveException;

    /**
     * Get figure position.
     * @return                                      position
     */
    public abstract Cell getPosition();

    public abstract Figure clone(Cell dest);

    /**
     * Get a color of a figure.
     * @return boolean
     */
    public abstract boolean getColor();
}
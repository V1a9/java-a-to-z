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
public abstract class Figure {
    /**
     * Variable that holds a cell position for a figure.
     */
    protected final Cell position;

    /**
     * Variable that defines a color of a figure.
     */
    private boolean white = false;
    /**
     * Constructor for the class.
     * @param aPosition                         initial cell coordinates
     * @param aWhite                            defines cell's color (white or black)
     */
    public Figure(Cell aPosition, boolean aWhite) {
        this.position = aPosition;
        this.white = aWhite;
    }
    /**
     * Method that checks correctness of a figure movement.
     * @param dest                                  coordinates of a cell where a figure is going to be moved
     * @return Cell[]                               an array of cells that a figure has to pass if a final cell is correct
     */
    abstract Cell[] way(Cell dest, Board board) throws ImpossibleMoveException;

}
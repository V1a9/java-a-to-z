package com.vgoryashko.chess.figures;

import com.vgoryashko.chess.game.Cell;
import com.vgoryashko.chess.game.ImpossibleMoveException;

/**
 * Class that implements a chess figure - Rook.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 19/12/2016
 */
public class Rook extends Figure {
    /**
     * Variable that stores current figure's position.
     */
    private Cell position;
    /**
     * Variable that stores figure's color.
     */
    private boolean color;
    /**
     * Constructor for the class.
     * @param aPosition                         initial cell coordinates
     * @param aColor                            defines cell's color (white or black)
     */
    public Rook(Cell aPosition, boolean aColor) {
        this.position = aPosition;
        this.color = aColor;
    }

    /**
     * Method that checks correctness of a figure movement.
     * @param dist                                  coordinates of a cell where a figure is going to be moved
     * @return Cell[]                               an array of cells that a figure has to pass if a final cell is correct
     * @throws ImpossibleMoveException              exception in case a wrong destination cell was entered
     */
    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        Cell[] result = null;
        return result;
    }
    /**
     * Method getter for Cell member.
     * @return Cell
     */
    @Override
    public Cell getPosition() {
        return this.position;
    }
    /**
     * Method getter for a figure's color.
     * @return boolean
     */
    public boolean getColor() {
        return this.color;
    }
    /**
     * Method that clones a figure with a given cell position.
     * @param dest                                  Given position of a figure.
     * @return Figure                               New figure.
     */
    @Override
    public Figure clone(Cell dest) {
        return new Bishop(dest, this.getColor());
    }
}

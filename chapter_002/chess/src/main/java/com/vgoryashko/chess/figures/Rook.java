package com.vgoryashko.chess.figures;

import com.vgoryashko.chess.game.Board;
import com.vgoryashko.chess.game.Cell;
import com.vgoryashko.chess.game.ImpossibleMoveException;

/**
 * Class that implements a chess figure - Rook.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 09/12/2016
 */
public class Rook extends Figure {

    /**
     * Constructor for the class.
     * @param aPosition                         initial cell coordinates
     * @param aWhite                            defines cell's color (white or black)
     */
    public Rook(Cell aPosition, boolean aWhite) {
        super(aPosition, aWhite);
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
     */
    @Override
    public Cell getPosition() {
        return super.position;
    }
    /**
     * Method getter for a figure's color.
     */
    public boolean getColor() {
        return super.color;
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

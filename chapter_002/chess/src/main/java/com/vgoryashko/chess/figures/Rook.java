package com.vgoryashko.chess.figures;

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
}

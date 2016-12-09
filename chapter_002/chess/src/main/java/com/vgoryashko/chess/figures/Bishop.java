package com.vgoryashko.chess.figures;

import com.vgoryashko.chess.game.*;

/**
 * Class that implements a chess figure - Bishop.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 09/12/2016
 */
public class Bishop extends Figure implements FigureMove{

    /**
     * Constructor for the class.
     * @param initPosition
     */
    public Bishop(Cell initPosition, boolean aWhite) {
        super(initPosition, aWhite);
    }

    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        Cell[] result = null;
        return result;
    }

    /**
     * Method that checks a possibility of a figure to make a move.
     *
     */
    public boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean result = false;
        return result;
    }
}

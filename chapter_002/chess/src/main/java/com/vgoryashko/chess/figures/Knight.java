package com.vgoryashko.chess.figures;

import com.vgoryashko.chess.game.Cell;
import com.vgoryashko.chess.game.ImpossibleMoveException;

/**
 * Class that implements a chess figure - Knight.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 09/12/2016
 */
public class Knight extends Figure {

    /**
     * Constructor for the class.
     * @param initPosition
     */
    public Knight(Cell initPosition, boolean aWhite) {
        super(initPosition, aWhite);
    }

    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        Cell[] result = null;
        return result;
    }
}

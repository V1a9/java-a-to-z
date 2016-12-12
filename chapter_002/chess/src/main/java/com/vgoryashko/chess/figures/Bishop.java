package com.vgoryashko.chess.figures;

import com.vgoryashko.chess.game.Cell;
import com.vgoryashko.chess.game.FigureNotFoundException;
import com.vgoryashko.chess.game.ImpossibleMoveException;
import com.vgoryashko.chess.game.OccupiedWayException;
import com.vgoryashko.chess.game.FigureMoveStrategy;

/**
 * Class that implements a chess figure - Bishop.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 09/12/2016
 */
public class Bishop extends Figure implements FigureMoveStrategy {

    /**
     * Constructor for the class.
     * @param aPosition                         initial cell coordinates
     * @param aWhite                            defines cell's color (white or black)
     */
    public Bishop(Cell aPosition, boolean aWhite) {
        super(aPosition, aWhite);
    }

    /**
     * Method that checks a possibility of a figure to make a move.
     * @param dist                                  defines coordinates of a destination cell
     * @return boolean
     * @throws ImpossibleMoveException              if a move isn't possible
     * @throws OccupiedWayException                 if a way is occupied
     * @throws FigureNotFoundException              if a figure not found
     */
    public boolean move(Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean result = false;
        return result;
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

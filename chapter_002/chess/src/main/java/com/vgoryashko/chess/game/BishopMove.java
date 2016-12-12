package com.vgoryashko.chess.game;

/**
 * Class that implements a chess board.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 12/12/2016
 */
public class BishopMove implements FigureMoveStrategy {
    /**
     * Method that checks a possibility of a figure to make a move.
     * @param dist   defines coordinates of a destination cell
     * @return boolean
     * @throws ImpossibleMoveException if a move isn't possible
     * @throws OccupiedWayException    if a way is occupied
     * @throws FigureNotFoundException if a figure not found
     */
    @Override
    public boolean move(Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean result = false;

        return result;
    }
}

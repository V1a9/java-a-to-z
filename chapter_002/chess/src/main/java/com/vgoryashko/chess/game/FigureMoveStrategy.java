package com.vgoryashko.chess.game;

/**
 * Interface that defines move algorithm of figure on a the chess board.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 09/12/2016
 */
public interface FigureMoveStrategy {
    /**
     * Method that checks a possibility of a figure to make a move.
     * @param dist                                  defines coordinates of a destination cell
     * @return boolean
     * @throws ImpossibleMoveException              if a move isn't possible
     * @throws OccupiedWayException                 if a way is occupied
     * @throws FigureNotFoundException              if a figure not found
     */
    boolean move(Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException;
}

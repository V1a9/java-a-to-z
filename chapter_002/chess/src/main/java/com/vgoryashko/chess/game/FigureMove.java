package com.vgoryashko.chess.game;

/**
 * Interface that defines move algorithm of figure on a the chess board.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 09/12/2016
 */
public interface FigureMove {
    /**
     * Method that checks a possibility of a figure to make a move.
     *
     */
    public boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException;
}

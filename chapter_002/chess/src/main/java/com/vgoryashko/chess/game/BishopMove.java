package com.vgoryashko.chess.game;

import com.vgoryashko.chess.figures.Figure;

/**
 * Class that implements a chess board.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 12/12/2016
 */
public class BishopMove implements FigureMoveStrategy {
    /**
     * Method that checks a possibility of a figure to make a move.
     * @param source                                defines coordinates of a source cell
     * @param dest                                  defines coordinates of a destination cell
     * @return boolean
     * @throws ImpossibleMoveException              if a move isn't possible
     * @throws OccupiedWayException                 if a way is occupied
     * @throws FigureNotFoundException              if a figure not found
     */
    @Override
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean result = false;
        try {
            if (source.getFigure() != null) {
                if (source.getCol())
            } else {
                throw new FigureNotFoundException("There is no such figure on the board.");
            }
        } catch (FigureNotFoundException fne) {
            System.out.println("Wrong source cell was chosen.");
        }
        return result;
    }
}

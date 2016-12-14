package com.vgoryashko.chess.figures;

import com.vgoryashko.chess.game.Board;
import com.vgoryashko.chess.game.Cell;
import com.vgoryashko.chess.game.ImpossibleMoveException;

/**
 * Class that implements a chess figure - Bishop.
 * @author Vlad Goryashko
 * @version 0.2
 * @since 14/12/2016
 */
public class Bishop extends Figure {
    /**
     * Constructor for the class.
     * @param aPosition                         initial cell coordinates
     * @param aWhite                            defines cell's color (white or black)
     */
    public Bishop(Cell aPosition, boolean aWhite) {
        super(aPosition, aWhite);
    }
    /**
     * Method that checks correctness of a figure movement.
     * @param dest                                  coordinates of a cell where a figure is going to be moved
     * @param aBoard                                allows access to members of the class Board
     * @return Cell[]                               an array of cells that a figure has to pass if a final cell is correct
     * @throws ImpossibleMoveException              exception in case a wrong destination cell was entered
     */
    public Cell[] way(Cell dest, Board aBoard) throws ImpossibleMoveException {
        Cell[] result = null;
        if ((Math.abs(super.position.getRow() - dest.getRow())) == (Math.abs(super.position.getCol() - dest.getCol()))) {
            result = new Cell[Math.abs(super.position.getRow() - dest.getRow())];
            for (int index = 1; index <= Math.abs(super.position.getCol() - dest.getCol()); index++) {
                if (aBoard.getCell(super.position.getRow() + index, super.position.getCol() + index).getFigure() == null) {
                    throw new ImpossibleMoveException("Impossible move.");
                } else {
                    result[index] = new Cell(super.position.getRow() + index, super.position.getCol() + index, super.position.getColor());
                }
            }
        } else {
            throw new ImpossibleMoveException("Impossible move.");
        }
        return result;
    }
    /**
     * Method getter for Cell member.
     */
    public Cell getCell() {
        return this.position;
    }
}

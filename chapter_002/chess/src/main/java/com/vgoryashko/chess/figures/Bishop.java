package com.vgoryashko.chess.figures;

import com.vgoryashko.chess.game.Cell;
import com.vgoryashko.chess.game.ImpossibleMoveException;

/**
 * Class that implements a chess figure - Bishop.
 * @author Vlad Goryashko
 * @version 0.3
 * @since 19/12/2016
 */
public class Bishop extends Figure {
    /**
     * Variable that stores current figure's position.
     */
    private Cell position;
    /**
     * Variable that stores figure's color.
     */
    private boolean color;
    /**
     * Constructor for the class.
     * @param aPosition                         initial cell coordinates
     * @param aColor                            defines cell's color (white or black)
     */
    public Bishop(Cell aPosition, boolean aColor) {
        this.position = aPosition;
        this.color = aColor;
    }
    /**
     * Method that checks correctness of a figure movement.
     * @param dest                                  coordinates of a cell where a figure is going to be moved
     * @return Cell[]                               an array of cells that a figure has to pass if a final cell is correct
     * @throws ImpossibleMoveException              exception in case a wrong destination cell was entered
     */
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] result = new Cell[Math.abs(this.getPosition().getRow() - dest.getRow())];
        if (Math.abs(this.getPosition().getCol() - dest.getCol()) == Math.abs(this.getPosition().getRow() - dest.getRow())) {
            if (this.getPosition().getRow() < dest.getRow() && this.getPosition().getCol() > dest.getCol()) {
                for (int index = 1; index <= Math.abs(this.getPosition().getRow() - dest.getRow()); index++) {
                    result[index - 1] = new Cell(this.getPosition().getRow() + index, this.getPosition().getCol() - index);
                }
            } else {
                if (this.getPosition().getRow() < dest.getRow() && this.getPosition().getCol() < dest.getCol()) {
                    for (int index = 1; index <= Math.abs(this.getPosition().getRow() - dest.getRow()); index++) {
                        result[index - 1] = new Cell(this.getPosition().getRow() + index, this.getPosition().getCol() + index);
                    }
                } else {
                    if (this.getPosition().getRow() > dest.getRow() && this.getPosition().getCol() < dest.getCol()) {
                        for (int index = 1; index <= Math.abs(this.getPosition().getRow() - dest.getRow()); index++) {
                            result[index - 1] = new Cell(this.getPosition().getRow() - index, this.getPosition().getCol() + index);
                        }
                    } else {
                        if (this.getPosition().getRow() > dest.getRow() && this.getPosition().getCol() > dest.getCol()) {
                            for (int index = 1; index <= Math.abs(this.getPosition().getRow() - dest.getRow()); index++) {
                                result[index - 1] = new Cell(this.getPosition().getRow() - index, this.getPosition().getCol() - index);
                            }
                        }
                    }
                }
            }
        } else {
            throw new ImpossibleMoveException("Bishop can't move there.");
        }
        return result;
    }
    /**
     * Method getter for Cell member.
     */
    @Override
    public Cell getPosition() {
        return this.position;
    }
    /**
     * Method getter for a figure's color.
     * @return boolean
     */
    public boolean getColor() {
        return this.color;
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

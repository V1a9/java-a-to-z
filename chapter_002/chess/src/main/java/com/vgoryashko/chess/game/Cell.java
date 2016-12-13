package com.vgoryashko.chess.game;

import com.vgoryashko.chess.figures.Figure;

/**
 * Class that defines a cell for a chess board.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 07/12/2016
 */
public class Cell {
    /**
     * Variable that holds a figure on a cell.
     */
    private Figure figure;
    /**
     * Variable that defines cell's color.
     */
    private final boolean white = true;
    /**
     * Variable that defines cell's color.
     */
    private final boolean black = false;
    /**
     * Variable that defines cell's color.
     */
    private boolean color;
    /**
     * Variable that holds a current position of a cell in a column (can be 0 - 7).
     */
    private int col;
    /**
     * Variable that holds a current position of a cell in a row (can be 0 - 7).
     */
    private int row;
    /**
     * Constructor for the class.
     * @param aCol                                  initializing a cell coordinate.
     * @param aRow                                  initializing a cell coordinate.
     * @param aWhite                                defining a cell's color.
     */
    public Cell(int aRow,  int aCol, boolean aWhite) {
        this.col = aCol;
        this.row = aRow;
        this.color = aWhite;
    }

    /**
     * Method getter for a color member.
     * @return boolean                              color of a cell (black or white)
     */
    public boolean getColor() {
        return this.color;
    }
    /**
     * Method getter for a row number of a cell.
     * @return int                              number of a cell's row (from 1 to 8)
     */
    public int getRow() {
        return this.row;
    }
    /**
     * Method getter for a column number of a cell.
     * @return int                              number of a cell's column (from 1 to 8)
     */
    public int getCol() {
        return this.col;
    }
    /**
     * Method getter for a Figure field.
     * @return Figure                            Figure stored in a cell
     */
    public Figure getFigure() {
        return this.figure;
    }
}

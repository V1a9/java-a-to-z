package com.vgoryashko.chess.game;

/**
 * Class that defines a cell for a chess board.
 * @author Vlad Goryashko
 * @version 0.2
 * @since 12/12/2016
 */
public class Cell {
    /**
     * Variable that holds a position of a cell in a column (can be 0 - 7).
     */
    private int col;
    /**
     * Variable that holds a position of a cell in a row (can be 0 - 7).
     */
    private int row;
    /**
     * Constructor for the class.
     * @param aCol                                  initializing a cell coordinate.
     * @param aRow                                  initializing a cell coordinate.
     */
    public Cell(int aRow,  int aCol) {
        this.col = aCol;
        this.row = aRow;
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
}

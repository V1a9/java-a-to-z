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
     * Variable that holds a destination position of a cell in a column (can be 0 - 7).
     */
    private int colDst;
    /**
     * Variable that holds a destination position of a cell in a column (can be 0 - 7).
     */
    private int rowDst;

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
        this.colDst = 0;
        this.rowDst = 0;
    }

    /**
     * Method getter for a color member.
     * @return boolean                              color of a cell (black or white)
     */
    public boolean getColor() {
        return this.color;
    }
}

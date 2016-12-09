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
    boolean white = false;
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
     * @param aColCurrent                           initializing a cell coordinate (X-axis).
     * @param aRowCurrent                           initializing a cell coordinate (Y-axis).
     */
    public Cell(int aColCurrent, int aRowCurrent, boolean aWhite) {
        this.col = aColCurrent;
        this.row = aRowCurrent;
        this.white = aWhite;
        this.colDst = 0;
        this.rowDst = 0;
    }
}

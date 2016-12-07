package com.vgoryashko.chess;

/**
 * Class that defines a cell for a chess board.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 07/12/2016
 */
public class Cell {
    /**
     * Variable that holds a value of a cell (X-axis -> can be 0 - 7).
     */
    private int indexX;
    /**
     * Variable that holds a value of a cell (Y-axis -> can be 0 - 7).
     */
    private int indexY;

    /**
     * Constructor for the class.
     * @param aIndexX                           initializing a cell coordinate (X-axis).
     * @param aIndexY                           initializing a cell coordinate (Y-axis).
     */
    public Cell(int aIndexX, int aIndexY) {
        this.indexX = aIndexX;
        this.indexY = aIndexY;
    }
}

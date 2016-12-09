package com.vgoryashko.chess.game;

import com.vgoryashko.chess.figures.Figure;

/**
 * Class that implements a chess board.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 07/12/2016
 */
public class Board {
    /**
     * Variable defines a qty of cells in a column.
     */
    private final int colSize = 8;
    /**
     * Variable defines a qty of cells in a row.
     */
    private final int rowSize = 8;
    /**
     * Array that defines all cells on the board.
     */
    private Cell[][] cellsOnBoard = new Cell[rowSize][colSize];
    /**
     * Variable that holds array of chess figures.
     */
    Figure[] figures;
}

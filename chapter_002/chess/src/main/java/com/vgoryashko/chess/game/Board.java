package com.vgoryashko.chess.game;

import com.vgoryashko.chess.figures.Figure;
import com.vgoryashko.chess.figures.Pawn;

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
     * Variable that defines cell's color.
     */
    private final boolean white = true;
    /**
     * Variable that defines cell's color.
     */
    private final boolean black = false;
    /**
     * Array that defines all cells on the board.
     */
    private Cell[][] cells = new Cell[rowSize][colSize];
    /**
     * Variable that holds array of chess figures.
     */
    Figure[] figures = new Figure[32];
    /**
     * Method that initialize board and cell.
     */
    public void init() {
        for (int indexRow = 0; indexRow < rowSize; indexRow++) {
            for (int indexCol = 0; indexCol < cells[indexRow].length; indexCol++) {
                if (indexRow%2 == 0) {
                    if (indexCol%2 == 0) {
                        cells[indexRow][indexCol] = new Cell(rowSize - indexRow, indexCol + 1, white);
                    } else {
                        cells[indexRow][indexCol] = new Cell(rowSize - indexRow, indexCol + 1, black);
                    }
                } else {
                    if (indexCol%2 == 0) {
                        cells[indexRow][indexCol] = new Cell(rowSize - indexRow, indexCol + 1, black);
                    } else {
                        cells[indexRow][indexCol] = new Cell(rowSize - indexRow, indexCol + 1, white);
                    }
                }
            }
        }
    }

    /**
     * Method that places figures on the board.
     */
    protected Figure[] setUpFigures() {
        for (int qty = 0; qty < rowSize; qty++) {
            figures[qty] = new Pawn(getCell(1, qty), black);
        }
        for (int qty = 0; qty < rowSize; qty++) {
            figures[qty + rowSize] = new Pawn(getCell(6, qty), white);
        }
        return figures;
    }

    /**
     * Method getter for retrieving of cells from the array.
     * @param indexRow                              number of a row a cell is in
     * @param indexCol                              number of a row a cell is in
     * @return                                      Cell object
     */
    public Cell getCell(int indexRow, int indexCol) {
        return this.cells[indexRow][indexCol];
    }

}

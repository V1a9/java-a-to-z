package com.vgoryashko.chess.game;

import com.vgoryashko.chess.figures.Figure;
import com.vgoryashko.chess.figures.Bishop;
import com.vgoryashko.chess.figures.Knight;
import com.vgoryashko.chess.figures.Rook;
import com.vgoryashko.chess.figures.King;
import com.vgoryashko.chess.figures.Queen;
import com.vgoryashko.chess.figures.Pawn;

import java.util.Arrays;

/**
 * Class that implements a chess board.
 * @author Vlad Goryashko
 * @version 0.2
 * @since 14/12/2016
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
    private boolean color;
    /**
     * Array that defines all cells on the board.
     */
    private Cell[][] cells = new Cell[rowSize][colSize];
    /**
     * Variable that holds array of chess figures.
     */
    protected Figure[] figures = new Figure[32];
    /**
     * Variable that is used as index for the array of figures.
     */
    private int figuresIndex = 0;
    /**
     * Variable that is used for operating with move strategies.
     */
    public Figure addFigure(Figure figure) {
        this.figures[figuresIndex++] = figure;
        return figure;
    }

    /**
     * Method that initialize board and cell.
     */
    public void initCells() {
        for (int indexRow = 0; indexRow < rowSize; indexRow++) {
            for (int indexCol = 0; indexCol < cells[indexRow].length; indexCol++) {
                if (indexRow % 2 == 0) {
                    if (indexCol % 2 == 0) {
                        cells[indexRow][indexCol] = new Cell(rowSize - indexRow, indexCol + 1, true);
                    } else {
                        cells[indexRow][indexCol] = new Cell(rowSize - indexRow, indexCol + 1, false);
                    }
                } else {
                    if (indexCol % 2 == 0) {
                        cells[indexRow][indexCol] = new Cell(rowSize - indexRow, indexCol + 1, false);
                    } else {
                        cells[indexRow][indexCol] = new Cell(rowSize - indexRow, indexCol + 1, true);
                    }
                }
            }
        }
    }

    /**
     * Method that places figures on the board.
     *
     * @return Figure[]                         array of figures after initialization.
     */
    protected Figure[] initFigures() {
        for (int qty = 0; qty < rowSize; qty++) {
            this.addFigure(new Pawn(getCell(1, qty), false));
        }
        for (int qty = 0; qty < rowSize; qty++) {
            this.addFigure(new Pawn(getCell(6, qty), true));
        }
        this.addFigure(new Rook(getCell(0, 0), false));
        this.addFigure(new Rook(getCell(0, 7), false));
        this.addFigure(new Knight(getCell(0, 1), false));
        this.addFigure(new Knight(getCell(0, 6), false));
        this.addFigure(new Bishop(getCell(0, 2), false));
        this.addFigure(new Bishop(getCell(0, 5), false));
        this.addFigure(new King(getCell(0, 3), false));
        this.addFigure(new Queen(getCell(0, 4), false));
        this.addFigure(new Rook(getCell(7, 0), true));
        this.addFigure(new Rook(getCell(7, 7), true));
        this.addFigure(new Knight(getCell(7, 1), true));
        this.addFigure(new Knight(getCell(7, 6), true));
        this.addFigure(new Bishop(getCell(7, 2), true));
        this.addFigure(new Bishop(getCell(7, 5), true));
        this.addFigure(new King(getCell(7, 3), true));
        this.addFigure(new Queen(getCell(7, 4), true));
        return this.figures;
    }

    /**
     * Method getter for retrieving of cells from the array.
     *
     * @param indexRow number of a row a cell is in
     * @param indexCol number of a row a cell is in
     * @return Cell object
     */
    public Cell getCell(int indexRow, int indexCol) {
        return this.cells[indexRow][indexCol];
    }

    /**
     * Method getter for retrieving of figures from the array.
     *
     * @param aFiguresIndex position of a figure in the array
     * @return Figure object
     */
    public Figure getFigure(int aFiguresIndex) {
        return this.figures[aFiguresIndex];
    }

}

package com.vgoryashko.chess.game;

import com.vgoryashko.chess.figures.Figure;
import com.vgoryashko.chess.figures.Bishop;
import com.vgoryashko.chess.figures.Knight;
import com.vgoryashko.chess.figures.Rook;
import com.vgoryashko.chess.figures.King;
import com.vgoryashko.chess.figures.Queen;
import com.vgoryashko.chess.figures.Pawn;

/**
 * Class that implements a chess board.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 07/12/2016
 */
public class Board {
    /**
     * Constructor for the class.
     */
    public Board(FigureMoveStrategy aFigureMoveStrategy) {
        this.figureMoveStrategy = aFigureMoveStrategy;
    }
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
    private Figure[] figures = new Figure[32];
    /**
     * Variable that is used for working with FigureMoveStrategy.
     */
    private FigureMoveStrategy figureMoveStrategy;
    /**
     * Variable that is used as index for the array of figures.
     */
    private int figuresIndex = 0;
    /**
     * Method that adds a figure to the array of figures.
     * @param figure                        a to be added into the array.
     * @return Figure                       a figure added to the array.
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
                        cells[indexRow][indexCol] = new Cell(rowSize - indexRow, indexCol + 1, white);
                    } else {
                        cells[indexRow][indexCol] = new Cell(rowSize - indexRow, indexCol + 1, black);
                    }
                } else {
                    if (indexCol % 2 == 0) {
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
     * @return Figure[]                         array of figures after initialization.
     */
    protected Figure[] initFigures() {
        for (int qty = 0; qty < rowSize; qty++) {
            this.addFigure(new Pawn(getCell(1, qty), black));
        }
        for (int qty = 0; qty < rowSize; qty++) {
            this.addFigure(new Pawn(getCell(6, qty), white));
        }
        this.addFigure(new Rook(getCell(0, 0), black));
        this.addFigure(new Rook(getCell(0, 7), black));
        this.addFigure(new Knight(getCell(0, 1), black));
        this.addFigure(new Knight(getCell(0, 6), black));
        this.addFigure(new Bishop(getCell(0, 2), black));
        this.addFigure(new Bishop(getCell(0, 5), black));
        this.addFigure(new King(getCell(0, 3), black));
        this.addFigure(new Queen(getCell(0, 4), black));
        this.addFigure(new Rook(getCell(7, 0), white));
        this.addFigure(new Rook(getCell(7, 7), white));
        this.addFigure(new Knight(getCell(7, 1), white));
        this.addFigure(new Knight(getCell(7, 6), white));
        this.addFigure(new Bishop(getCell(7, 2), white));
        this.addFigure(new Bishop(getCell(7, 5), white));
        this.addFigure(new King(getCell(7, 3), white));
        this.addFigure(new Queen(getCell(7, 4), white));
        return this.figures;
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

    /**
     * Method getter for retrieving of cells from the array.
     * @param aFiguresIndex                          position of a figure in the array
     * @return                                      Figure object
     */
    public Figure getFigure(int aFiguresIndex) {
        return this.figures[aFiguresIndex];
    }

    /**
     * Method that checks a possibility to move a figure.
     */
    public boolean move(Cell dest) {
        return figureMoveStrategy.move(dest);
    }
}

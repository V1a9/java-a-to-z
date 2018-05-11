package com.vgoryashko.chess.game;

import com.vgoryashko.chess.figures.Figure;
import com.vgoryashko.chess.figures.Bishop;
import com.vgoryashko.chess.figures.Pawn;

/**
 * Class that implements a chess board.
 * @author Vlad Goryashko
 * @version 0.2
 * @since 19/12/2016
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
    private Figure[] figures = new Figure[32];
    /**
     * Variable that is used as index for the array of figures.
     */
    private int figuresIndex = 0;
    /**
     * Variable that is used for operating with move strategies.
     * @param figure                        a figure to be placed on the board.
     * @return Figure
     */
    public Figure addFigure(Figure figure) {
        this.figures[figuresIndex++] = figure;
        return figure;
    }

    /**
     * Method that initialize cells.
     */
    public void initCells() {
        for (int indexRow = 0; indexRow < rowSize; indexRow++) {
            for (int indexCol = 0; indexCol < cells[indexRow].length; indexCol++) {
                cells[indexRow][indexCol] = new Cell(indexRow, indexCol);
            }
        }
    }

    /**
     * Method that places figures on the board.
     *
     * @return Figure[]                         array of figures after initialization.
     */
    protected Figure[] initFigures() {
        this.addFigure(new Bishop(getCell(0, 2), false));
        this.addFigure(new Bishop(getCell(0, 5), false));
        this.addFigure(new Pawn(getCell(1, 4), false));
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

    /**
     * Method that check a possibility to move a figure and performs movement.
     * @param source                            source cell
     * @param dest                              destination cell
     * @return boolean
     * @throws FigureNotFoundException          if figure wasn't found
     * @throws ImpossibleMoveException          if move isn't possible
     * @throws OccupiedWayException             if way is occupied
     */
    public boolean move(Cell source, Cell dest) throws FigureNotFoundException, ImpossibleMoveException, OccupiedWayException {
        boolean result = false;
        Figure movingFigure = null;
        Cell[] wayCell = null;
        int cycleCounter = 1;
        for (int index = 0; index < this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].getPosition().getCol() == source.getCol() && this.figures[index].getPosition().getRow() == source.getRow()) {
                movingFigure = this.figures[index];
                wayCell = this.figures[index].way(dest);
                break;
            } else if (index == this.figures.length - 1) {
                throw new FigureNotFoundException("There is no figure.");
            } else {
                continue;
            }
        }
        for (Cell aCell : wayCell) {
            for (int index = 0; index < wayCell.length; index++) {
                if (this.figures != null && this.figures[index].getPosition().getRow() == aCell.getRow() && this.figures[index].getPosition().getCol() == aCell.getCol()) {
                    throw new OccupiedWayException("Way is occupied.");
                }
            }
            if (cycleCounter == wayCell.length) {
                movingFigure.clone(dest);
                result = true;
                break;
            }
            cycleCounter++;
        }
        return result;
    }
}

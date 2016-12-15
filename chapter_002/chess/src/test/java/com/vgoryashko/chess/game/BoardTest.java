package com.vgoryashko.chess.game;

import com.vgoryashko.chess.figures.Figure;
import com.vgoryashko.chess.figures.Pawn;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class that tests the class board.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 11/12/2016
 */
public class BoardTest {

    /**
     * Variable used for operating with the Board class.
     */
    private Board board;

    /**
     * Method that set-ups testing environments before each test case.
     */
    @Before
    public void setUpEnvironment() {
        board = new Board();
    }

    /**
     * Method that tests instantiation of a new Board.
     */
    @Test
    public void whenNewBoardInstantiatedItsNotNull() {
        assertNotNull(board);
    }

    /**
     * Method that tests correctness of cells creation.
     */
    @Test
    public void whenCellsAreCreatedThenBoardCorrectlySetUp() {
        board.initCells();
        assertTrue(board.getCell(0, 0).getColor());
        assertFalse(board.getCell(0, 7).getColor());
        assertFalse(board.getCell(7, 0).getColor());
        assertTrue(board.getCell(7, 7).getColor());
    }

    /**
     * Method that tests addFigure method.
     */
    @Test
    public void whenFigureIsAddedItIsnNull() {
        Figure figure = new Pawn(new Cell(0, 0, true), true);
        assertThat(board.addFigure(figure), is(board.getFigure(0)));
    }
//    /**
//     * Method that tests correctness of figures placement.
//     */
//    @Test
//    public void whenFiguresSetUPThenFiguresCorrectlySetUp() {
//        board.initCells();
//        assertNotNull(board.initFigures());
//    }
    @Test
    public void moveFigureTest() {
        board.initCells();
        board.initFigures();
        assertThat(board.moveFigure(), is(board.getFigure(1)));
    }
}

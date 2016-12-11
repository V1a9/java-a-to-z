package com.vgoryashko.chess.game;

import com.vgoryashko.chess.figures.Figure;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class that tests the class board.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 11/12/2016
 */
public class BoardTest {

    private Board board;

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
        board.init();
        assertTrue(board.getCell(0, 0).getColor());
        assertFalse(board.getCell(0, 7).getColor());
        assertFalse(board.getCell(7, 0).getColor());
        assertTrue(board.getCell(7, 7).getColor());
    }

    /**
     * Method that tests correctness of figures placement.
     */
    @Test
    public void whenFiguresSetUPThenFiguresCorrectlySetUp() {
        board.init();
        assertNotNull(board.setUpFigures());
    }
}

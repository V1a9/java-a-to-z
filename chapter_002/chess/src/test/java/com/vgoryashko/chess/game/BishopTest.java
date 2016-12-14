package com.vgoryashko.chess.game;

import com.vgoryashko.chess.figures.Bishop;
import com.vgoryashko.chess.figures.Figure;
import com.vgoryashko.chess.figures.Pawn;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class that tests a chess figure - Bishop.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 14/12/2016
 */
public class BishopTest {

    /**
     * Variable used for operating with the Board class.
     */
    private Board board;
    /**
     * Rule for testing exceptions.
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    /**
     * Method that set-ups testing environments before each test case.
     */
    @Before
    public void setUpEnvironment() {
        board = new Board();
        board.initCells();
    }

    /**
     * Method that tests instantiation of a Bishop object.
     */
    @Test
    public void whenBishopIsInstantiatedThenItsNotNull() {
        assertNotNull(new Bishop(new Cell(1, 1, true), true));
    }
    /**
     * Method that tests way method of the Bishop figure.
     */
    @Test
    public void whenMovementIsPerformedThenRightWayIsDepicted() {
        board.initFigures();
//        board.
    }
}

package com.vgoryashko.chess.game;

import com.vgoryashko.chess.figures.Bishop;
import com.vgoryashko.chess.figures.Figure;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        assertNotNull(new Bishop(new Cell(1, 1), true));
    }
    /**
     * Method that tests way method of the Bishop figure.
     */
    @Test
    public void whenMovementIsPerformedThenRightWayIsDepicted() {
        Figure aFigure = new Bishop(new Cell(4, 3), true);
        Cell[] wayCell1 = aFigure.way(new Cell(6, 1));
        assertTrue(wayCell1[0].getRow() == 5 & wayCell1[0].getCol() == 2);
        assertTrue(wayCell1[1].getRow() == 6 & wayCell1[1].getCol() == 1);
        Cell[] wayCell2 = aFigure.way(new Cell(6, 5));
        assertTrue(wayCell2[0].getRow() == 5 & wayCell2[0].getCol() == 4);
        assertTrue(wayCell2[1].getRow() == 6 & wayCell2[1].getCol() == 5);
        Cell[] wayCell3 = aFigure.way(new Cell(2, 1));
        assertTrue(wayCell3[0].getRow() == 3 & wayCell3[0].getCol() == 2);
        assertTrue(wayCell3[1].getRow() == 2 & wayCell3[1].getCol() == 1);
        Cell[] wayCell4 = aFigure.way(new Cell(2, 5));
        assertTrue(wayCell4[0].getRow() == 3 & wayCell4[0].getCol() == 4);
        assertTrue(wayCell4[1].getRow() == 2 & wayCell4[1].getCol() == 5);
    }
    /**
     * Method that tests possibility to move a bishop incorrectly.
     */
    @Test
    public void whenWrongDestinationSetThenError() {
        expectedException.expectMessage("Bishop can't move there.");
        Figure aFigure = new Bishop(new Cell(4, 3), true);
        Cell[] wayCell1 = aFigure.way(new Cell(3, 5));
        throw new ImpossibleMoveException("Bishop can't move there.");
    }

}

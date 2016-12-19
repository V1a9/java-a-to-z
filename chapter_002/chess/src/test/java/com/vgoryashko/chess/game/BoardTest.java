package com.vgoryashko.chess.game;

import com.vgoryashko.chess.figures.Figure;
import com.vgoryashko.chess.figures.Pawn;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class that tests the class board.
 * @author Vlad Goryashko
 * @version 0.2
 * @since 19/12/2016
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
     * Rule for testing exceptions.
     */
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Method that tests instantiation of a new Board.
     */
    @Test
    public void whenNewBoardInstantiatedItsNotNull() {
        assertNotNull(board);
    }

    /**
     * Method that tests addFigure method.
     */
    @Test
    public void whenFigureIsAddedItIsnNull() {
        Figure figure = new Pawn(new Cell(0, 0), true);
        assertThat(board.addFigure(figure), is(board.getFigure(0)));
    }
    /**
     * Method that tests correctness of cells creation.
     */
    @Test
    public void whenCellsAreCreatedThenBoardCorrectlySetUp() {
        board.initCells();
        Cell cell1 = board.getCell(0, 0);
        assertTrue(cell1.getRow() == 0 && cell1.getCol() == 0);
        Cell cell2 = board.getCell(0, 7);
        assertTrue(cell2.getRow() == 0 && cell2.getCol() == 7);
        Cell cell3 = board.getCell(7, 0);
        assertTrue(cell3.getRow() == 7 && cell3.getCol() == 0);
        Cell cell4 = board.getCell(7, 7);
        assertTrue(cell4.getRow() == 7 && cell4.getCol() == 7);
    }
    /**
     * Method that tests throwing of ImpossibleMoveException in the move() method.
     */
    @Test
    public void whenWrongDestinationGivenThenExceptionIsThrown() {
        expectedException.expectMessage("Bishop can't move there.");
        board.initCells();
        board.initFigures();
        board.move(board.getCell(0, 5), new Cell(1, 2));
        throw new ImpossibleMoveException("Bishop can't move there.");
    }
    /**
     * Method that tests throwing of FigureNotFoundException in the move() method.
     */
    @Test
    public void whenWrongSourceCellIsGivenThenExceptionIsThrown() {
        expectedException.expect(FigureNotFoundException.class);
        board.initCells();
        board.initFigures();
        board.move(board.getCell(0, 4), new Cell(1, 4));
        throw new FigureNotFoundException("There is no figure.");
    }
    /**
     * Method that tests throwing of OccupiedWayException in the move() method.
     */
    @Test
    public void whenWayIsOccupiedThenExceptionIsThrown() {
        expectedException.expect(OccupiedWayException.class);
        board.initCells();
        board.initFigures();
        board.move(board.getCell(0, 5), new Cell(2, 3));
        throw new OccupiedWayException("Way is occupied.");
    }

    /**
     * Method that tests that figure is moved.
     */
    @Test
    public void whenCorrectSourceAndDestinationAreGivenThenFigureIsMoved() {
        board.initCells();
        board.initFigures();
        assertTrue(board.move(board.getCell(0, 5), new Cell(2, 7)));
    }
}

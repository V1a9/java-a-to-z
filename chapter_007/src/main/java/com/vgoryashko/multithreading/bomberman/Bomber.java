package com.vgoryashko.multithreading.bomberman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that defines Bomber hero with its behavior.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 10/19/17
 */
public final class Bomber extends HeroType {

    /**
     * Constant that stores reference to the Logger.
     */
    private final Logger logger = LogManager.getLogger(Bomber.class.getName());

    /**
     * Constant value that determines condition when user move was read by processInput Thread.
     */
    private static final String EMPTY = "EMPTY";

    /**
     * Constant that defines move of the Bomber UP.
     */
    private static final String UP = "W";

    /**
     * Constant that defines move of the Bomber DOWN.
     */
    private static final String DOWN = "S";

    /**
     * Constant that defines move of the Bomber RIGHT.
     */
    private static final String RIGHT = "F";

    /**
     * Constant that defines move of the Bomber LEFT.
     */
    private static final String LEFT = "A";

    /**
     * Constant that defines QUITE flag.
     */
    private static final String QUIT = "Q";

    /**
     * Field that refers to an instance of the Thread.
     */
    private final Thread readInput;

    /**
     * Thread that processes an user's move input.
     */
    private final Thread processInput;

    /**
     * Field that refers to an instance of InputStream used for reading an user's move input.
     */
    private final InputStream inputStream;

    /**
     * Field that stores a referents to a Cell that Bomber stays on.
     */
    private ReentrantLock currentCell;

    /**
     * Field that refers to the object that represents game board.
     */
    private final ReentrantLock[][] board;

    /**
     * Field that refers to an instance of Semaphore.
     */
    private final Semaphore semaphore;

    /**
     * Field that stores a value for an user's move.
     */
    private String userMove = EMPTY;

    /**
     * Constructor for the class.
     *
     * @param hero type of the hero
     * @param board game board
     * @param currentCell of the Bomber
     * @param inputStream to be used for retrieving of an user's input data
     */
    public Bomber(boolean hero, ReentrantLock[][] board, ReentrantLock currentCell, InputStream inputStream) {
        super(hero);
        this.board = board;
        this.semaphore = new Semaphore(1);
        this.currentCell = currentCell;
        this.inputStream = inputStream;
        logger.debug("Bomber created currentCell is: " + currentCell);
    }

    {
        readInput = new Thread() {

            /**
             * If this thread was constructed using a separate
             * <code>Runnable</code> run object, then that
             * <code>Runnable</code> object's <code>run</code> method is called;
             * otherwise, this method does nothing and returns.
             * <p>
             * Subclasses of <code>Thread</code> should override this method.
             *
             * @see #start()
             * @see #stop()
             */
            @Override
            public void run() {
                logger.traceEntry(" into the readInput run()...");
                userInput(inputStream);
                logger.traceExit("Exited from the readInput run()...");
            }
        };

    }

    {

        processInput = new Thread() {

            /**
             * If this thread was constructed using a separate
             * <code>Runnable</code> run object, then that
             * <code>Runnable</code> object's <code>run</code> method is called;
             * otherwise, this method does nothing and returns.
             * <p>
             * Subclasses of <code>Thread</code> should override this method.
             *
             * @see #start()
             * @see #stop()
             */
            @Override
            public void run() {

                logger.traceEntry(" into the processInput run()...");

                currentCell.lock();

                while (!isInterrupted()) {

                    logger.debug(userMove);
                    moveBomber(userMove);
                    userMove = EMPTY;
                    semaphore.release();
                    logger.debug("release() done");
                }

                logger.traceExit("Exited from the processInput run()...");
            }
        };

    }

    /**
     * Method that finds current position of the hero.
     *
     * @return int[] array of coordinates
     */
    public int[] findPosition() {

        logger.traceEntry("find position invoked");

        int[] position = new int[2];

        for (int i = 0; i < this.board.length; i++) {

            for (int j = 0; j < this.board[i].length; j++) {

                if (this.currentCell.equals(this.board[i][j])) {

                    position[0] = i;
                    position[1] = j;

                }
            }
        }

        return logger.traceExit("Init position has returned: " + Arrays.toString(position), position);

    }

    /**
     * Method that reads an user's move input data.
     * @param inputStream InputStream
     */
    public void userInput(InputStream inputStream) {

        logger.traceEntry(" into userInput()...");

        String inputChar;

        try (Scanner scanner = new Scanner(inputStream)) {

            while (!readInput.isInterrupted()) {

                try {
                    this.semaphore.acquire();
                    logger.debug("Semaphore acquire() done...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                inputChar = scanner.nextLine().toUpperCase();

                logger.trace("Input received: " + inputChar);

                if (inputChar.equals(QUIT)) {
                    logger.traceExit("Input received: " + inputChar);
                    readInput.interrupt();
                    processInput.interrupt();
                    break;
                } else {
                    this.userMove = inputChar;
                    logger.trace("User input is: " + inputChar);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.traceExit("Exited from userInput()...");
    }

    /**
     * Method that moves the Bomber.
     *
     * @param userMove input from user
     */
    public void moveBomber(String userMove) {

        logger.traceEntry("into moveBomber()...");

        ReentrantLock nextMove;

        int[] position = this.findPosition();

        int row = position[0];
        int col = position[1];

        switch (userMove) {

            case UP:
                if (row - 1 >= 0) {
                    --row;
                }
                break;
            case RIGHT:
                if (col + 1 <= this.board[row].length - 1) {
                    ++col;
                }
                break;
            case DOWN:
                if (row + 1 <= this.board.length - 1) {
                    ++row;
                }
                break;
            case LEFT:
                if (col - 1 >= 0) {
                    --col;
                }
                break;
            default:
                break;
        }

        nextMove = this.board[row][col];

        if (nextMove.tryLock()) {
            logger.debug("Moved to new cell: row " + row + ", col " + col);
            this.currentCell.unlock();
            this.currentCell = nextMove;
        }

        logger.traceExit("Next cell proposed: ", new int[]{row, col});
    }

    /**
     * Getter for the currentCell.
     * @return ReentrantLock current cell
     */
    public ReentrantLock getCurrentCell() {
        return this.currentCell;
    }

    /**
     * Getter for readInput field.
     * @return Thread
     */
    public Thread getReadInput() {
        return this.readInput;
    }

    /**
     * Getter for processInput field.
     * @return Thread
     */
    public Thread getProcessInput() {
        return this.processInput;
    }
}

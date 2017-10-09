package com.vgoryashko.multithreading.bomberman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that defines Bomber hero with its behavior.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 10/9/17
 */
public final class Bomber extends HeroType implements Runnable {

    /**
     * Constant that stores reference to the Logger.
     */
    private final static Logger logger = LogManager.getLogger();

    /**
     * Field that refers to an instance of the Thread.
     */
    private final Thread thread;

    /**
     * Field that stores the constant value for the number of possible moves for the Hero.
     */
    private final static int MOVES = 5;

    /**
     * Field that stores a referents to a Cell that Bomber stays on.
     */
    private ReentrantLock currentCell;

    /**
     * Field that refers to the object that represents game board.
     */
    private final ReentrantLock[][] board;

    /**
     * Constructor for the class.
     * @param hero hero type
     * @param currentCell cell that hero stays on
     */
    public Bomber(boolean hero, ReentrantLock[][] board, ReentrantLock currentCell) {

        super(hero);
        this.board = board;
        this.thread = new Thread(this,"Bomber thread");
        this.currentCell = currentCell;

        logger.debug("Bomber instance created");

    }

    /**
     * Method that finds current position of the hero.
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
     * Method that defines a free cell and moves a Bomber.
     */
    public int[] move(int move, int row, int col) {

        logger.traceEntry("Parameters: move " + move + ", row " + row + ", col " + col);

        switch (move) {

            case 0:
                if (row - 1 >= 0) {
                    --row;
                }
                break;
            case 1:
                if (col + 1 < this.board[row].length - 1) {
                    ++col;
                }
                break;
            case 2:
                if (row + 1 <= this.board.length - 1 && col - 1 >= 0) {
                    ++row;
                    --col;
                }
                break;
            case 3:
                if (col + 1 < this.board[row].length && row + 1 < this.board.length) {
                    ++row;
                    ++col;
                }
                break;
            case 4:
                if (row + 1 < this.board.length) {
                    ++row;
                }
                break;
        }

        return logger.traceExit("Next move proposed: row " + row + ", col " + col, new int[]{row, col});

    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        logger.traceEntry("Run invoked");

        ReentrantLock nextCell;

        this.currentCell.lock();

        int[] position = findPosition();

        int row = position[0];
        int col = position[1];

        Random randomMove = new Random();

        while (true) {

            int[] newRowCol = move(randomMove.nextInt(MOVES), row, col);

            try {
                if ((nextCell = this.board[newRowCol[0]][newRowCol[1]]).tryLock(500, TimeUnit.MILLISECONDS)) {
                    this.currentCell.unlock();
                    this.currentCell = nextCell;
                    row = newRowCol[0];
                    col = newRowCol[1];
                    logger.trace("New move done to: row " + row + ", col " + col);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * Getter for the thread.
     */
    public Thread getThread() {
        return this.thread;
    }

    /**
     * Getter for the currentCell.
     */
    public ReentrantLock getCurrentCell() {
        return this.currentCell;
    }
}

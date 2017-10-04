package com.vgoryashko.multithreading.bomberman;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that defines Bomber hero with its behavior.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 10/4/17
 */
public final class Bomber extends HeroType implements Runnable {

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
        this.thread = new Thread(this,"Bomber thread.");
        this.currentCell = currentCell;
    }

    /**
     * Method that finds current position of the hero.
     */
    public int[] findPosition() {

        int[] position = new int[2];

        for (int i = 0; i < this.board.length; i++) {

            for (int j = 0; j < this.board[i].length; j++) {

                if (this.currentCell.equals(this.board[i][j])) {

                    position[0] = i;
                    position[1] = j;

                }
            }
        }

        return position;

    }

    /**
     * Method that defines a free cell and moves a Bomber.
     */
    public int[] move(int move, int row, int col) {

        ReentrantLock nextCell;

        switch (move) {

            case 0:
                if (row - 1 >= 0) {

                    try {
                        if ((nextCell = this.board[row - 1][col]).tryLock(500, TimeUnit.MILLISECONDS)) {
                            this.currentCell.unlock();
                            this.currentCell = nextCell;
                            --row;
                            System.out.println("row " + row + " col " + col);
                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 1:
                if (col + 1 < this.board[row].length - 1) {

                    try {
                        if ((nextCell = this.board[row][col + 1]).tryLock(500, TimeUnit.MILLISECONDS)) {
                            this.currentCell.unlock();
                            this.currentCell = nextCell;
                            ++col;
                            System.out.println("row " + row + " col " + col);
                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                if (row + 1 <= this.board.length - 1 && col - 1 >= 0) {

                    try {
                        if ((nextCell = this.board[row + 1][col - 1]).tryLock(500, TimeUnit.MILLISECONDS)) {
                            this.currentCell.unlock();
                            this.currentCell = nextCell;
                            ++row;
                            --col;
                            System.out.println("row " + row + " col " + col);

                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case 3:
                if (col + 1 < this.board[row].length && row + 1 < this.board.length) {

                    try {
                        if ((nextCell = this.board[row + 1][col + 1]).tryLock(500, TimeUnit.MILLISECONDS)) {
                            this.currentCell.unlock();
                            this.currentCell = nextCell;
                            ++row;
                            ++col;
                            System.out.println("row " + row + " col " + col);

                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            case 4:
                if (row + 1 < this.board.length) {

                    try {
                        if ((nextCell = this.board[row + 1][col]).tryLock(500, TimeUnit.MILLISECONDS)) {
                            this.currentCell.unlock();
                            this.currentCell = nextCell;
                            ++row;
                            System.out.println("row " + row + " col " + col);

                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }

        return new int[]{row, col};

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

        this.currentCell.lock();

        int[] position = findPosition();

        int row = position[0];
        int col = position[1];

        Random randomMove = new Random();

        System.out.println("row " + row + " col " + col);

        while (true) {

            int move  = randomMove.nextInt(MOVES);

            int[] newRowCol = move(move, row, col);

            row = newRowCol[0];
            col = newRowCol[1];

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

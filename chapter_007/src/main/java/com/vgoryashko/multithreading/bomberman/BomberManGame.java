package com.vgoryashko.multithreading.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that runs the main logic of the Bomber man game.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 10/4/17
 */
public class BomberManGame {

    /**
     * Field that refers to the game board represented by an Array.
     */
    private final ReentrantLock[][] gameBoard;

    /**
     * Constructor for the class.
     *
     * @param row number of rows
     * @param col number of column
     */
    public BomberManGame(int row, int col) {
        this.gameBoard = new ReentrantLock[row][col];
    }

    /**
     * Method that initializes a game setup.
     */
    public void init() {

        for (int i = 0; i < this.gameBoard.length; i++) {
            for (int j = 0; j < this.gameBoard[i].length; j++) {

                gameBoard[i][j] = new ReentrantLock();

            }
        }
    }

    /**
     * Getter for the gameBoard field.
     */
    public ReentrantLock[][] getGameBoard() {
        return this.gameBoard;
    }

}

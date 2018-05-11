package com.vgoryashko.chess.game;

/**
 * Class that implements a chess game.
 * @author Vlad Goryashko
 * @version 0.1
 * @since 09/12/2016
 */
public class Game {

    /**
     * Method that starts a game.
     */
    public void start() {
        Board board = new Board();
        board.initCells();
        board.initFigures();
    }
    /**
     * Entering point for the program.
     * @param args                          default parameter for the main method.
     */
    public static void main(String[] args) {
        new Game().start();
    }
}

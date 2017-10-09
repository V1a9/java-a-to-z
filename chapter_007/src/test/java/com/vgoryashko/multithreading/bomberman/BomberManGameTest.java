package com.vgoryashko.multithreading.bomberman;

import org.junit.Before;
import org.junit.Test;

/**
 * Class that tests Bomber man application.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 10/9/17
 */
public class BomberManGameTest {

    /**
     * Field that refers to an instance of BomberManGame.
     */
    private BomberManGame game;

    /**
     * Method that setups test environments.
     * @throws Exception Exception
     */
    @Before
    public void setUp() throws Exception {

        this.game = new BomberManGame(2, 2);

    }

    /**
     * todo
     */
    @Test
    public void test() {

        game.init();
        Bomber bomber = new Bomber(true, this.game.getGameBoard(), this.game.getGameBoard()[1][1]);

        this.game.getGameBoard()[0][0].tryLock();
//        this.game.getGameBoard()[0][1].tryLock();
        this.game.getGameBoard()[1][0].tryLock();

        bomber.getThread().start();

        try {
            bomber.getThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
package com.vgoryashko.multithreading.bomberman;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class that tests Bomber man application.
 *
 * @author Vlad Goryashko
 * @version 0.4
 * @since 10/19/17
 */
public class BomberManGameTest {

    /**
     * Constant that defines new line string.
     */
    private static final String NL = System.getProperty("line.separator");

    /**
     * Field that refers to an instance of the InputStream.
     */
    private InputStream inputStream;

    /**
     * Method that tests reading of an user's input and moves the Bomber.
     */
    @Test
    public void whenUserInputReceivedThenBomberMovesAsExpected() {

        BomberManGame game = new BomberManGame(2, 2);

        game.init();

        this.inputStream = new ByteArrayInputStream(
                Joiner.on(NL).join(
                        "a",
                        "w",
                        "d",
                        "s",
                        "q").getBytes()
        );

        Bomber bomber = new Bomber(true, game.getGameBoard(), game.getGameBoard()[1][1], inputStream);

        Thread t1 = bomber.getReadInput();
        Thread t2 = bomber.getProcessInput();

        t1.start();
        t2.start();

        assertThat(bomber.getCurrentCell(), is(game.getGameBoard()[1][1]));

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method that tests findPosition().
     */
    @Test
    public void whenMonstersFindPositionInvokedThenCellsCoordinatesReturned() {

        BomberManGame game = new BomberManGame(2, 2);

        game.init();

        Monster monster = new Monster(false, game.getGameBoard(), game.getGameBoard()[1][1]);

        assertThat(monster.findPosition(), is(new int[]{1, 1}));

    }

    /**
     * Method that tests Monster.move().
     */
    @Test
    public void whenMonsterMoveInvokedThenNextProposedCellReturned() {

        BomberManGame game = new BomberManGame(3, 3);

        game.init();

        Monster monster = new Monster(false, game.getGameBoard(), game.getGameBoard()[1][1]);

        assertThat(monster.move(0, 1, 1), is(new int[]{0, 1}));

        assertThat(monster.move(1, 1, 1), is(new int[]{1, 2}));

        assertThat(monster.move(2, 1, 1), is(new int[]{2, 0}));

        assertThat(monster.move(3, 1, 1), is(new int[]{2, 2}));

        assertThat(monster.move(4, 1, 1), is(new int[]{2, 1}));


    }

}
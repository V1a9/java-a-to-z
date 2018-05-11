package com.vgoryashko.multithreading.wait.lock;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class that tests implementation of the Lock mechanism.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 9/24/17
 */
public class LockTest {

    /**
     * Rule that allows to verify that code throws InterruptedException.
     */
    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**
     * Field that stores int value.
     */
    private int increment;

    /**
     * Method that initializes test environments.
     */
    @Before
    public void init() {

        this.increment = 0;

    }

    /**
     * Method that tests the Lock mechanism.
     *
     * @throws InterruptedException InterruptedException
     */
    @Test
    public void whenVariableLockedThenOnlyOneThreadCanModifyIt() throws InterruptedException {

        Lock lock = new Lock();

        Thread t1 = new Thread() {

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
                ++increment;
            }
        };

        Thread t2 = new Thread() {

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
                increment += 2;
            }
        };

        lock.lock();
        t1.start();
        lock.unLock();
        t2.start();
        t1.join();
        t2.join();

        assertThat(this.increment, is(3));

    }
}
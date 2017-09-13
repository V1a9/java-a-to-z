package com.vgoryashko.multithreading.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class that demonstrates synchronized methods.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/13/17
 */
public class Synchronized {

    /**
     * Class that consists of a state variable and the method that increment this variable.
     */
    @ThreadSafe
    private class Counter {

        /**
         * Variable that stores qty of counts.
         */
        @GuardedBy("this") private int counter = 0;

        /**
         * Method that increments counter.
         *
         * @param value int
         * @return long
         */
        public synchronized int increment(int value) {

            this.counter += value;
            return this.counter;

        }
    }

    /**
     * Class that creates a thread and executes increment of the state variable in the object counter.
     */
    private class MultiThreadCounter extends Thread {

        /**
         * Instance variable of a class Counter.
         */
        private final Counter counter;

        /**
         * Constructor for the class.
         *
         * @param counter an instance of the Counter
         */
        MultiThreadCounter(Counter counter) {

            this.counter = counter;

        }

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
            counter.increment(2);
        }
    }

    /**
     * Implements main logic.
     *
     * @throws InterruptedException InterruptedException
     */
    public void startApp() throws InterruptedException {

        Counter counter = new Counter();
        MultiThreadCounter threadA = new MultiThreadCounter(counter);
        MultiThreadCounter threadB = new MultiThreadCounter(counter);

        threadA.start();
        threadB.start();

        threadB.join();

        System.out.print(counter.counter);
    }

}

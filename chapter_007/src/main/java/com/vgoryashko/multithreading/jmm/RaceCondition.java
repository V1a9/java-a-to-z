package com.vgoryashko.multithreading.jmm;

/**
 * Class that demonstrates Race Condition issue.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 9/11/17
 */
public class RaceCondition {


    /**
     * Instance variable that stores int value.
     */
    /**
     * Instance variable that stores int value.
     */
    private int value;

    /**
     * Constructor for the class.
     */
    RaceCondition() {

        this.value = 10;

    }

    /**
     * Class that defines thread 1.
     */
    private class Thread1 implements Runnable {

        /**
         * Variable that used for storing an int value.
         */
        private int thread1Value;

        /**
         * Thread of the object.
         */
        private Thread thread1;

        /**
         * Constructor for the class.
         */
        Thread1() {

            this.thread1 = new Thread(this);

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

            value = ++value;
            this.thread1Value = value;

        }

    }

    /**
     * Class that defines thread 2.
     */
    private class Thread2 implements Runnable {

        /**
         * Variable that used for storing an int value.
         */
        private int thread2Value;

        /**
         * Thread of the object.
         */
        private Thread thread2;

        /**
         * Constructor for the class.
         */
        Thread2() {

            this.thread2 = new Thread(this);

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

            value = ++value;
            this.thread2Value = value;

        }
    }

    /**
     * Method that starts application.
     *
     * @return int
     */
    public int startApp() {

        new  Thread1().thread1.start();
        new  Thread2().thread2.start();

        return getValue();

    }

    /**
     * Getter for the value.
     *
     * @return int
     */
    public int getValue() {
        return this.value;
    }


}

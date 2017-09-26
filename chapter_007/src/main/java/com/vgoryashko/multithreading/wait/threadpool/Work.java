package com.vgoryashko.multithreading.wait.threadpool;

/**
 * Interface that defines a contract for the Work.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/26/17
 */
public class Work implements Runnable {

    /**
     * TODO
     */
    private int number;

    /**
     * Constructor for the class.
     */
    public Work(int newNumber) {

        this.number = newNumber;

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

        for (int i = 0; i < this.number; i++) {

            if (i == this.number - 1) {
                System.out.println(Thread.currentThread() + " number " + (i + 1));
            }

        }
    }
}
package com.vgoryashko.multithreading.wait.threadpool;

import java.util.List;

/**
 * Interface that defines a contract for the Work.
 *
 * @author Vlad Goryashko
 * @version 0.3
 * @since 9/29/17
 */
public class Work implements Runnable {

    /**
     * Variable that stores an int number.
     */
    private int number;

    /**
     * Collection that stores a result of a work.
     */
    private List<Integer> result;

    /**
     * Constructor for the class.
     *
     * @param newNumber for work's counter
     * @param result collection to store a result of work
     */
    public Work(int newNumber, List<Integer> result) {

        this.number = newNumber;
        this.result = result;

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
                result.add(i + 1);
            }
        }
    }
}
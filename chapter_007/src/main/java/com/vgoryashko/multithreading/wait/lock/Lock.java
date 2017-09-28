package com.vgoryashko.multithreading.wait.lock;

/**
 * Class that implements lock mechanism for concurrent applications.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 9/24/17
 */
public class Lock {

    /**
     * Variable that stores a value for the flag that defines if locked.
     */
    private boolean lock = false;

    /**
     * Method that locks a piece of a code for a concurrent access on an objects of this class.
     */
    public synchronized void lock() {

        while (this.lock) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.lock = true;
        }
    }

    /**
     * Method that unlocks a piece of code.
     */
    public synchronized void unLock() {

        this.lock = false;
        this.notifyAll();

    }

}

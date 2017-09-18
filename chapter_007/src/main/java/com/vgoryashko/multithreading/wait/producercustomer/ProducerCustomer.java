package com.vgoryashko.multithreading.wait.producercustomer;

import net.jcip.annotations.ThreadSafe;

/**
 * Class that implements data structure in which Producer provides data
 * and Customer retrieves it when available (based on blocking queue).
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/17/17
 */
@ThreadSafe
public class ProducerCustomer {

    /**
     * Instance member that refers to collection data structure.
     */
    private int readData;

    /**
     * Variable that store a value for a thread block flag.
     */
    private boolean blockRead = true;

    /**
     * Method that writes data into the class storage.
     *
     * @param element array of data
     */
    public void write(int element) {

        synchronized (this) {

            while (!blockRead) {

                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            this.readData = element;
            this.blockRead = false;
            this.notifyAll();
        }
    }

    /**
     * Method that retrieves data from the queue.
     *
     * @return integer
     */
    public int read() {

        synchronized (this) {

            while (blockRead) {

                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            this.blockRead = true;
            this.notifyAll();
            return this.readData;
        }


    }

}
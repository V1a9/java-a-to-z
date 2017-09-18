package com.vgoryashko.multithreading.wait.producercustomer;

import java.util.Random;

/**
 * Thread that adds data into the collection.
 */
public class Producer extends Thread {

    /**
     * Variable that refers to an instance of ProducerCustomer.
     */
    private ProducerCustomer producerCustomer;

    /**
     * Variable that refers to an array of an input data.
     */
    private int[] data;

    /**
     * Constructor for the class.
     *
     * @param producerCustomer ProducerCustomer
     */
    Producer(ProducerCustomer producerCustomer) {

        this.producerCustomer = producerCustomer;
        this.data = new int[]{1, 2, 3, 4, 5};

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

        Random random = new Random();

        for (int i = 0; i < this.data.length; i++) {

            producerCustomer.write(this.data[i]);

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        producerCustomer.write(-1);

    }
}
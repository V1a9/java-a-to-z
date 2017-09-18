package com.vgoryashko.multithreading.wait.producercustomer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Thread that retrieves data from the collection.
 */
public class Customer extends Thread {

    /**
     * Variable that refers to an instance of ProducerCustomer.
     */
    private ProducerCustomer producerCustomer;

    /**
     * Variable that refers to an instance of the collection for storage of data.
     */
    private final List<Integer> list = new ArrayList<>();

    /**
     * Constructor for the class.
     *
     * @param producerCustomer ProducerCustomer
     */
    Customer(ProducerCustomer producerCustomer) {

        this.producerCustomer = producerCustomer;

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

        Random random = new Random();

        for (int data = producerCustomer.read(); data != -1; data = producerCustomer.read()) {

            this.list.add(data);

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Getter for the member list.
     *
     * @return List of Integers
     */
    public List<Integer> getList() {
        return this.list;
    }
}
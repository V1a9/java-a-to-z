package com.vgoryashko.multithreading.wait.producercustomer;

import net.jcip.annotations.ThreadSafe;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Class that implements data structure in which Producer provides data
 * and Customer retrieves it when available (based on blocking queue).
 *
 * @param <T> type of data to be used in the application
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/17/17
 */
@ThreadSafe
public class ProducerCustomer<T extends Number> {

    /**
     * Instance member that refers to collection data structure.
     */
    private final Collection<T> queue;

    /**
     * Variable that store a value for a thread block flag.
     */
    private boolean blockThread = true;

    /**
     * Constructor for the class.
     */
    ProducerCustomer() {
        this.queue = Collections.synchronizedCollection(new LinkedList<>());
    }

    /**
     * Method that writes data into the class storage.
     *
     * @param element array of data
     */
    public void write(T[] element) {

        synchronized (this.queue) {

            Collections.addAll(this.queue, element);
            this.blockThread = false;
            this.queue.notify();

        }


    }

    /**
     * Method that retrieves data from the queue.
     *
     * @return Collection<T> collection
     */
    public Collection<T> read() {

        Collection<T> temp = new LinkedList<>();

        synchronized (this.queue) {

            while (blockThread) {

                try {

                    this.queue.wait();

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

            }

            for (T element : this.queue) {

                System.out.println(element);

            }

        }

        return temp;

    }

    /**
     * Thread that adds data into the collection.
     */
    public class Producer extends Thread {

        /**
         * Variable that refers to an instance of ProducerCustomer.
         */
        private ProducerCustomer<T> producerCustomer;

        /**
         * Variable that refers to an array of an input data.
         */
        private T[] data;

        /**
         * Constructor for the class.
         *
         * @param producerCustomer ProducerCustomer
         * @param inputData data to be written by Producer
         */
        Producer(ProducerCustomer<T> producerCustomer, T[] inputData) {

            this.producerCustomer = producerCustomer;
            this.data = inputData;

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

            this.producerCustomer.write(this.data);

        }
    }

    /**
     * Thread that retrieves data from the collection.
     */
    public class Customer extends Thread {

        /**
         * Variable that refers to an instance of ProducerCustomer.
         */
        private ProducerCustomer<T> producerCustomer;

        /**
         * Member that refers to Customer's storage of data.
         */
        private Collection<T> customerData;

        /**
         * Constructor for the class.
         *
         * @param producerCustomer ProducerCustomer
         */
        Customer(ProducerCustomer<T> producerCustomer) {

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

            this.customerData = this.producerCustomer.read();

        }

        /**
         * Getter for the customerData.
         *
         * @return Collection of elements read from the app's storage
         */
        public Collection<T> getCustomerData() {

            return this.customerData;

        }
    }

    /**
     * Method that implements main logic of the application.
     *
     * @param producerCustomer an instance of ProducerCustomer
     * @param inputData data to be written by Producer
     * @return Collection of read data
     * @throws InterruptedException InterruptedException
     */
    public Collection<T> startApplication(ProducerCustomer producerCustomer, T[] inputData) throws InterruptedException {

        Producer producer = new Producer(producerCustomer, inputData);
        Customer customer = new Customer(producerCustomer);

        producer.start();
        customer.start();

        producer.join();
        customer.join();

        return customer.getCustomerData();

    }

}

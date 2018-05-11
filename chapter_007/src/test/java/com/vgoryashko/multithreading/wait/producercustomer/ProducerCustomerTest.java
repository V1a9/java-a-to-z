package com.vgoryashko.multithreading.wait.producercustomer;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Class that test implementation of the applications Producer Customer.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/17/17
 */
public class ProducerCustomerTest {

    /**
     * Variable that refers to an instance of the ProducerCustomer class.
     */
    private ProducerCustomer producerCustomer;

    /**
     * Variable that receives data from the Customer.
     */
    private List<Integer> actual;

    /**
     * Method that initializes tests.
     * @throws Exception
     */
    @Before
    public void setUp() {

        this.producerCustomer =  new ProducerCustomer();

    }

    /**
     * Method that test ProducerCustomer class.
     *
     * @throws InterruptedException InterruptedException
     */
    @Test
    public void whenStartAppInvokedThenDataPutByProducerCorrectlyReadByCustomer() throws InterruptedException {


        Producer producer = new Producer(producerCustomer);
        Customer customer = new Customer(producerCustomer);

        Thread producerThread = new Thread(producer);
        Thread customerThread = new Thread(customer);

        producerThread.start();
        customerThread.start();

        producerThread.join();
        customerThread.join();

        this.actual = customer.getList();

        Integer[] expected = new Integer[]{1, 2, 3, 4, 5};
        int index = 0;

        for (Integer element : actual) {
            assertEquals(element, expected[index++]);

        }
    }
}
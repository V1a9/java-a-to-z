package com.vgoryashko.multithreading.wait.producercustomer;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

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
    private ProducerCustomer<Integer> producerCustomer;

    /**
     * Variable that receives data from the Customer.
     */
    private Collection<Integer> actual;

    /**
     * Member that stores final data for input.
     */
    private final Integer[] inputData = new Integer[]{1, 2, 3, 4, 5};

    /**
     * Method that initializes tests.
     * @throws Exception
     */
    @Before
    public void setUp() {

        this.producerCustomer =  new ProducerCustomer<>();

    }

    /**
     * Method that test ProducerCustomer class.
     *
     * @throws InterruptedException InterruptedException
     */
    @Test
    public void whenStartAppInvokedThenDataPutByProducerCorrectlyReadByCustomer() throws InterruptedException {

        this.actual = producerCustomer.startApplication(this.producerCustomer, this.inputData);

        Integer[] expected = new Integer[]{1, 2, 3, 4, 5};
        int index = 0;

        for (Integer element : actual) {
            System.out.println(element);
            assertEquals(element, expected[index++]);

        }
    }

}
package com.vgoryashko.multithreading.wait.cache;

/**
 * Class that implements RuntimeException that reports that a model version doesn't fit
 * to the version requirements and can't be overridden.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 9/28/17
 */
public class OptimisticException extends RuntimeException {

    /**
     * Constructor for the class.
     * @param message to be displayed by the Exception
     */
    public OptimisticException(String message) {
        super(message);
    }

}

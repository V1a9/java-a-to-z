package com.vgoryashko.interactivecalc;

/**
 * Interface that defines input/output contracts for the implementation of IO functionality.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/10/17
 */
public interface IO {

    /**
     * Method that implements reading of input data from the console.
     *
     * @param question                  question to an end-user while entering a command.
     * @return <code>double</code>
     */
    double read(String question);
}

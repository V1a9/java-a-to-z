package com.vgoryashko.interactivecalc;

/**
 * Interface that defines input/output contracts for the implementation of Input functionality.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 5/19/17
 */
public interface Input {

    /**
     * Method that implements reading of input data from the console.
     *
     * @param question                  question to an end-user while entering a command.
     * @return <code>double</code>
     */
    double read(String question);
}

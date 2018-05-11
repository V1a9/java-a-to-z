package com.vgoryashko.interactivecalc;

/**
 * Interface that defines contracts for operations that have to be implemented in InteractCalc.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/10/17
 */
public interface UserAction {

    /**
     * Method that indicates a operation that user is going to perform.
     *
     * @return                              <code>int</code>
     */
    int key();

    /**
     * Method that executes an operation.
     *
     * @param calculator                    Calculator object that performs calculations.
     */
    void execute(Calculator calculator);

    /**
     * Method that depicts info about an operation is being performed.
     *
     * @return                              <code>String</code>
     */
    String info();

}

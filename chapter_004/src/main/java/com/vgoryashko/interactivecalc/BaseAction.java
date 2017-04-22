package com.vgoryashko.interactivecalc;

/**
 * Abstract class that defines base class for user action.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/10/17
 */
public abstract class BaseAction implements UserAction {

    /**
     * Variable that stores a name of an operation.
     */
    private String name;

    /**
     * The default constructor for the class.
     *
     * @param operationName                     a name of an operation.
     */
    public BaseAction(String operationName) {
        this.name = operationName;
    }

    /**
     * Method that indicates a operation that user is going to perform.
     *
     * @return <code>int</code>
     */
    public abstract int key();

    /**
     * Method that executes an operation.
     *
     * @param io         Input/output stream.
     * @param calculator Calculator object that performs calculations.
     */
    public abstract void execute(IO io, Calculator calculator);

    /**
     * Method that depicts info about an operation is being performed.
     *
     * @return <code>String</code>
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }
}

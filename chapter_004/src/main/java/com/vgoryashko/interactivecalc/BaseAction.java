package com.vgoryashko.interactivecalc;

/**
 * Abstract class that defines base class for user action.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 5/19/17
 */
public abstract class BaseAction implements UserAction {

    /**
     * Variable that stores a name of an operation.
     */
    private String name;

    /**
     * Variable that stores previous flag.
     */
    private static boolean previousRes = false;

    /**
     * Variable that stores previous operation flag.
     */
    private static int previousOp = 0;

    /**
     * Setter for the member.
     * @param previousOp true if previous result to be used.
     */
    public static void setPreviousOp(int previousOp) {
        BaseAction.previousOp = previousOp;
    }

    /**
     * Getter for the member previousOp.
     * @return operation number
     */
    public static int getPreviousOp() {
        return previousOp;
    }

    /**
     * Setter for the member.
     * @param previous true if previous result to be used.
     */
    public static void setPreviousRes(boolean previous) {
        BaseAction.previousRes = previous;
    }

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
     * @param calculator Calculator object that performs calculations.
     */
    public abstract void execute(Calculator calculator);

    /**
     * Method that depicts info about an operation is being performed.
     *
     * @return <code>String</code>
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key(), this.name);
    }

    /**
     * Method that gets input data based on command entered by an user.
     *
     * @param input input method
     * @param calculator instance of calculator
     * @return array of doubles
     */
    public double[] getInput(Input input, Calculator calculator) {
        double[] result = new double[2];
        if (!BaseAction.previousRes) {
            result[0] = input.read("Enter 1st operand: ");
            result[1] = input.read("Enter 2nd operand: ");
        } else {
            result[0] = calculator.getResult();
            result[1] = input.read("Enter 2nd operand: ");
            BaseAction.previousRes = false;
        }
        return result;
    }

}

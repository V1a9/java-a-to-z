package com.vgoryashko.interactivecalc;

/**
 * Class that.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/10/17
 */
public class Operation {

    /**
     * Inner class.
     */
    private class Addition extends BaseAction {
        /**
         * Constructor for the class.
         *
         * @param operationName name of the operation.
         */
        Addition(String operationName) {
            super(operationName);
        }

        /**
         * Method that indicates a operation that user is going to perform.
         *
         * @return <code>int</code>
         */
        @Override
        public int key() {
            return 1;
        }

        /**
         * Method that executes an operation.
         *
         * @param io         Input/output stream.
         * @param calculator Calculator object that performs calculations.
         */
        @Override
        public void execute(IO io, Calculator calculator) {
            double operand1 = io.read("Enter 1st operand: ");
            double operand2 = io.read("Enter 2nd operand: ");
            calculator.addition(operand1, operand2);
        }
    }

    /**
     * Inner class.
     */
    private class Substraction extends BaseAction {

        /**
         * Constructor for the class.
         *
         * @param operationName name of the operation.
         */
        Substraction(String operationName) {
            super(operationName);
        }

        /**
         * Method that indicates a operation that user is going to perform.
         *
         * @return <code>int</code>
         */
        @Override
        public int key() {
            return 0;
        }

        /**
         * Method that executes an operation.
         *
         * @param io         Input/output stream.
         * @param calculator Calculator object that performs calculations.
         */
        @Override
        public void execute(IO io, Calculator calculator) {

        }
    }
}

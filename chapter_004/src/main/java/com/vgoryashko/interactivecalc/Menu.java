package com.vgoryashko.interactivecalc;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements a menu for the application.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 5/19/17
 */
public class Menu {

    /**
     * Variable that stores menu elements.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Variable that is used for operating with Calculator.
     */
    private Calculator calculator;

    /**
     * Variable that is used for operating with Input.
     */
    private Input input;

    /**
     * Constructor for the class.
     *
     * @param aCalculator object to be used for calculating operations.
     * @param aInput Input system to be used for interaction with an end-user.
     */
    public Menu(Calculator aCalculator, Input aInput) {
        this.calculator = aCalculator;
        this.input = aInput;
    }

    /**
     * Inner class that is used for adding operation.
     */
    private class Add extends BaseAction {
        /**
         * Constructor for the class.
         *
         * @param operationName name of the operation.
         */
        Add(String operationName) {
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
         * @param calculator Calculator object that performs calculations.
         */
        @Override
        public void execute(Calculator calculator) {
            double[] inputData = getInput(input, calculator);
            calculator.addition(inputData[0], inputData[1]);
            BaseAction.setPreviousOp(this.key());
        }
    }

    /**
     * Inner class that is used for substraction operation.
     */
    private class Substract extends BaseAction {

        /**
         * Constructor for the class.
         *
         * @param operationName name of the operation.
         */
        Substract(String operationName) {
            super(operationName);
        }

        /**
         * Method that indicates a operation that user is going to perform.
         *
         * @return <code>int</code>
         */
        @Override
        public int key() {
            return 2;
        }

        /**
         * Method that executes an operation.
         *
         * @param calculator Calculator object that performs calculations.
         */
        @Override
        public void execute(Calculator calculator) {
            double[] inputData = getInput(input, calculator);
            calculator.substraction(inputData[0], inputData[1]);
            BaseAction.setPreviousOp(this.key());
        }
    }

    /**
     * Inner class that is used for multiplication operation.
     */
    private class Multiply extends BaseAction {
        /**
         * Constructor for the class.
         *
         * @param operationName name of the operation.
         */
        Multiply(String operationName) {
            super(operationName);
        }

        /**
         * Method that indicates a operation that user is going to perform.
         *
         * @return <code>int</code>
         */
        @Override
        public int key() {
            return 3;
        }

        /**
         * Method that executes an operation.
         *
         * @param calculator Calculator object that performs calculations.
         */
        @Override
        public void execute(Calculator calculator) {
            double[] inputData = getInput(input, calculator);
            calculator.multiplication(inputData[0], inputData[1]);
            BaseAction.setPreviousOp(this.key());
        }
    }

    /**
     * Inner class that is used for division operation.
     */
    private class Divide extends BaseAction {
        /**
         * Constructor for the class.
         *
         * @param operationName name of the operation.
         */
        Divide(String operationName) {
            super(operationName);
        }

        /**
         * Method that indicates a operation that user is going to perform.
         *
         * @return <code>int</code>
         */
        @Override
        public int key() {
            return 4;
        }

        /**
         * Method that executes an operation.
         *
         * @param calculator Calculator object that performs calculations.
         */
        @Override
        public void execute(Calculator calculator) {
            double[] inputData = getInput(input, calculator);
            calculator.division(inputData[0], inputData[1]);
            BaseAction.setPreviousOp(this.key());
        }
    }

    /**
     * Inner class that chose previous operation.
     */
    private class UsePreviousOperation extends BaseAction {
        /**
         * Constructor for the class.
         *
         * @param operationName name of the operation.
         */
        UsePreviousOperation(String operationName) {
            super(operationName);
        }

        /**
         * Method that indicates a operation that user is going to perform.
         *
         * @return <code>int</code>
         */
        @Override
        public int key() {
            return 5;
        }

        /**
         * Method that executes an operation.
         *
         * @param calculator Calculator object that performs calculations.
         */
        @Override
        public void execute(Calculator calculator) {
            selectOperation(BaseAction.getPreviousOp());
        }
    }

    /**
     * Inner class that is allows calculation with a previous result.
     */
    private class UsePreviousResult extends BaseAction {
        /**
         * Constructor for the class.
         *
         * @param operationName name of the operation.
         */
        UsePreviousResult(String operationName) {
            super(operationName);
        }

        /**
         * Method that indicates a operation that user is going to perform.
         *
         * @return <code>int</code>
         */
        @Override
        public int key() {
            return 6;
        }

        /**
         * Method that executes an operation.
         *
         * @param calculator Calculator object that performs calculations.
         */
        @Override
        public void execute(Calculator calculator) {
            BaseAction.setPreviousRes(true);
        }
    }

    /**
     * Inner class that exits from the application.
     */
    private class Exit extends BaseAction {

        /**
         * Constructor for the class.
         *
         * @param operationName name of the operation.
         */
        Exit(String operationName) {
            super(operationName);
        }

        /**
         * Method that indicates a operation that user is going to perform.
         *
         * @return <code>int</code>
         */
        @Override
        public int key() {
            return 7;
        }

        /**
         * Method that executes an operation.
         *
         * @param calculator Calculator object that performs calculations.
         */
        @Override
        public void execute(Calculator calculator) {
        }
    }

    /**
     * Method that fills the menu with commands.
     */
    void fillActions() {
        actions.add(new Add("Add;"));
        actions.add(new Substract("Substract;"));
        actions.add(new Multiply("Multiply;"));
        actions.add(new Divide("Divide;"));
        actions.add(new UsePreviousOperation("Perform previous operation;"));
        actions.add(new UsePreviousResult("Calculation with previous result;"));
        actions.add(new Exit("Exit."));

    }

    /**
     * Method that selects an operation based on a key (assigned to each one).

     * @param key                               value assigned to each operation in the menu.
     */
    void selectOperation(int key) {
        this.actions.get(key - 1).execute(this.calculator);
    }

    /**
     * Method that prints menu.
     */
    void showMenu() {
        for (UserAction action : actions) {
            System.out.println(action.info());
        }
    }
}

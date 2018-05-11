package com.vgoryashko.interactivecalc;

import java.util.Scanner;

/**
 * Class that implements interactive calculator.
 * User is able to enter a data using Input interface.
 * Repetitive selection of an operation and usage of
 * a result from a previous operation.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 5/19/17
 */
public class InteractCalc {

    /**
     * Variable that is being used for operating with the Calculator class.
     */
    private final Calculator calculator;

    /**
     * Variable that is being used for operating with the Input system.
     */
    private final Input input;

    /**
     * Variable that is referring to Menu instance.
     */
    private final Menu menu;

    /**
     * Variable that stores a flag of exit.
     */
    private boolean exit = false;

    /**
     * Constant that stores a line separator value.
     */
    private static final String LS = System.getProperty("line.separator");

    /**
     * Constructor for the class.
     *
     * @param aCalculator object to be used for calculating operations.
     * @param aInput Input system to be used for interaction with an end-user.
     */
    public InteractCalc(Calculator aCalculator, Input aInput) {
        this.calculator = aCalculator;
        this.input = aInput;
        this.menu = new Menu(this.calculator, this.input);
    }

    /**
     * Method that starts the application.
     */
    public void start() {
        int key;
        menu.fillActions();
        do {
            menu.showMenu();
            key = (int) (this.input.read("Chose operation: "));
            if (key == 1) {
                menu.selectOperation(1);
                System.out.format("Result is: %f%s", this.calculator.getResult(), LS);
            } else if (key == 2) {
                menu.selectOperation(2);
                System.out.format("Result is: %f%s", this.calculator.getResult(), LS);
            } else if (key == 3) {
                menu.selectOperation(3);
                System.out.format("Result is: %f%s", this.calculator.getResult(), LS);
            } else if (key == 4) {
                menu.selectOperation(4);
                System.out.format("Result is: %f%s", this.calculator.getResult(), LS);
            } else if (key == 5) {
                menu.selectOperation(5);
                System.out.format("Previous operation is: %f%s", this.calculator.getResult(), LS);
            } else if (key == 6) {
                menu.selectOperation(6);
                System.out.format("Previous result is: %f%s", this.calculator.getResult(), LS);
            } else if (key == 7) {
                menu.selectOperation(7);
                System.out.format("Exiting...%s", LS);
                exit = true;
            }

        } while (!exit);
    }

    /**
     * Method that is an entry point.
     *
     * @param args standard parameter for the main() method.
     */
    public static void main(String[] args) {
        new InteractCalc(new Calculator(), new ConsoleInput(new Scanner(System.in))).start();
    }
}

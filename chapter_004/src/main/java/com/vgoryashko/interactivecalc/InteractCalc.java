package com.vgoryashko.interactivecalc;

/**
 * Class that implements interactive calculator.
 * User is able to enter a data using IO interface.
 * Repetitive selection of an operation and usage of
 * a result from a previous operation.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/10/17
 */
public class InteractCalc {

    /**
     * Variable that is being used for operating with the Calculator class.
     */
    private final Calculator calculator;

    /**
     * Variable that is being used for operating with the IO system.
     */
    private final IO io;

    /**
     * Constructor for the class.
     *
     * @param aCalculator object to be used for calculating operations.
     * @param aIo IO system to be used for interaction with an end-user.
     */
    public InteractCalc(Calculator aCalculator, IO aIo) {
        this.calculator = aCalculator;
        this.io = aIo;
    }

    /**
     * Method that starts the application.
     */
    public void start() {
        Menu menu = new Menu(this.calculator, this.io);
        int key;
        do {

            menu.showMenu();
            key = (int) (this.io.read("Chose operation: "));
            if (key == 1) {
                menu.selectOperation(1);
                System.out.println(this.calculator.getResult());
            }

        } while (key != 7);
    }

    /**
     * Method that is an entry point.
     *
     * @param args standard parameter for the main() method.
     */
    public static void main(String[] args) {
        new InteractCalc(new Calculator(), new ConsoleInput()).start();
    }
}

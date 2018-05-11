package com.vgoryashko.interactivecalc;

import java.util.Scanner;

/**
 * Class that implements console input.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 5/19/17
 */
public class ConsoleInput implements Input {

    /**
     * Variable that stores Scanner instance.
     */
    private Scanner scanner;

    /**
     * Constructor for the class.
     * @param scanner to be used for reading from the console.
     */
    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Method that implements reading of input data from the console.
     *
     * @param question question to an end-user while entering a command.
     * @return <code>double</code>
     */
    @Override
    public double read(String question) {
        System.out.printf("%s\n", question);
        return scanner.nextDouble();
    }
}

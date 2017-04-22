package com.vgoryashko.interactivecalc;

import java.util.Scanner;

/**
 * Class that implements console input.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 4/10/17
 */
public class ConsoleInput implements IO {

    /**
     * Method that implements reading of input data from the console.
     *
     * @param question question to an end-user while entering a command.
     * @return <code>double</code>
     */
    @Override
    public double read(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s\n", question);
        return scanner.nextDouble();
    }
}

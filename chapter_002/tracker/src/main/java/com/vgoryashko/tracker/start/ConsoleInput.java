package com.vgoryashko.tracker.start;

import java.util.Scanner;

/**
 * Class that is used for interacting with an user via console.
 * @author Vlad Goryashko
 * @since 11.21.2016
 * @version 1.0
 */
public class ConsoleInput implements Input {

    /**
     * Object that is used for implementation of receiving input data via console.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Method that depicts a menu to an end user and receiving input data.
     * @param question                  an option in the UI to be displayed for an end user to chose from
     * @return                          an string result based on an user input and the menu items
     */
    public String ask(String question) {
        System.out.printf("%s: ", question);
        return scanner.nextLine();
    }
}

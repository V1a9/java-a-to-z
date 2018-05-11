package com.vgoryashko.tracker.start;

import java.util.Scanner;

/**
 * Class that is used for interacting with an user via console.
 * @author Vlad Goryashko
 * @since 01.12.2016
 * @version 2.0
 */
public class ConsoleInput implements Input {

    /**
     * Object that is used for implementation of receiving input data via console.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Method that depicts a menu to an end user and receiving input data.
     * @param question                  an option in the UI to be displayed for an end user to chose from
     * @return                          a string result based on an user input and the menu items
     */
    public String ask(String question) {
        System.out.printf("%s ", question);
        return scanner.nextLine();
    }

    /**
     * Method that is used for reading inputs from user via console based on the menu.
     * @param question                  option in the UI to be displayed for an end user to chose from
     * @param range                     range of valid input values available for an user to choose from
     * @return                          return int key
     */
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exists = false;
        for (int value : range) {
            if (key == value) {
                exists = true;
                break;
            }
        }
        if (exists) {
            return key;
        } else {
            throw new MenuOutExceptions("Out of the menu range.");
        }
    }
}

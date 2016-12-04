package com.vgoryashko.tracker.start;

/**
 * Class that is used for validation of user input via console.
 * @author Vlad Goryashko
 * @since 03.12.2016
 * @version 1.0
 */
public class ValidateInput extends ConsoleInput {
    /**
     * Method that is used for reading inputs from user via console and validates input.
     * @param question                  option in the UI to be displayed for an end user to chose from
     * @param range                     range of valid input values available for an user to choose from
     * @return                          return int key
     */
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutExceptions moe) {
                moe.printStackTrace();
                System.out.println("\nPlease select a correct option from the menu. ");
            } catch (NumberFormatException nfe) {
                System.out.println("\nPlease enter valid data. ");
            }
        } while (invalid);
        return value;
    }
}

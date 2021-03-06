package com.vgoryashko.tracker.start;

/**
 * Public interface that declares methods for interacting with end user.
 */
public interface Input {

    /**
     * Method that is used for reading inputs from user via console based on the menu.
     * @param question                  option in the UI to be displayed for an end user to chose from
     * @return                          return String
     */
    String ask(String question);

    /**
     * Method that is used for reading inputs from user via console based on the menu.
     * @param question                  option in the UI to be displayed for an end user to chose from
     * @param range                     range of valid input values available for an user to choose from
     * @return                          return int key
     */
    int ask(String question, int[] range);
}

package com.vgoryashko.tracker.start;

/**
 * Class that is used for automatic tests of UI.
 * @author Vlad Goryashko
 * @version 4.0
 * @since 30/10/2017
 */
public class StubInput implements Input {

    /**
     * Array of programmed answers for modelling user behaviour.
     */
    private String[] answers;

    /**
     * Variable used for access to the elements of array answers.
     */
    private int position = 0;

    /**
     * Constructor for the class.
     * @param aAnswers                      programmed answers to be tested by the class.
     */
    public StubInput(String[] aAnswers) {
        this.answers = aAnswers;
    }

    /**
     * Method that implements method from the Interface Input.
     * @param question                      questions that implement a menu for an user
     * @return                              answers from the array
     */
    public String ask(String question) {
            return answers[position++];
    }

    /**
     * Method that implements method from the Interface Input.
     * @param question                      questions that implement a menu for an user
     * @param range                         range of valid input values available for an user to choose from
     * @return                              answers from the array
     */
    public int ask(String question, int[] range) {
        return Integer.parseInt(answers[position++]);
    }
}

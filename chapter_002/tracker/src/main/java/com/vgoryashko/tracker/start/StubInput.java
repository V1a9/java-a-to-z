package com.vgoryashko.tracker.start;

/**
 * Class that is used for automatic tests of UI.
 * @author Vlad Goryashko
 * @since 11.22.2016
 * @version 1.0
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
}

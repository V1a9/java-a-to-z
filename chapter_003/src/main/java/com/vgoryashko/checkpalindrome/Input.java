package com.vgoryashko.checkpalindrome;

/**
 * Interface that defines input methods.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 06.02.2017
 */
public interface Input {

    /**
     * Method that defines an input method.
     * @param question              Question to an end-user
     * @return String               Word entered by an end-user in console
     */
    String input(String question);
}

package com.vgoryashko.checkpalindrome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that implements Input interface and input method.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 06.02.2017
 */
public class ConsoleInput implements Input {

    /**
     * Method that defines an input method.
     * @param question              Question to an end-user
     * @return String               Word entered by an end-user in console
     */
    @Override
    public String input(String question) {
        String result = null;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(question);
            result = reader.readLine();
        } catch (IOException ioe) {
            System.out.println("IOException in input() method.");
        }
        return result;
    }
}

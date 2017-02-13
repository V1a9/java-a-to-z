package com.vgoryashko.consolechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class that implements input methods.
 *
 * @author Vlad Goryashko
 * @version 0.2
 * @since 2/11/17
 */
public class In implements Input {

    /**
     * Method that implements input method.
     * @return                      String
     */
    @Override
    public String in() {
        String result = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            result = reader.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    /**
     * Method that defines input method with String[] parameter.
     *
     * @return                                  String
     * @param strings                           an array of strings to be used for input
     * @param index                             an index of a string to be returned
     */
    @Override
    public String in(String[] strings, int index) {
        return strings[index];
    }
}

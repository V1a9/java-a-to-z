package com.vgoryashko.consolechat;

/**
 * Interface that describes input methods.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 2/11/17
 */
public interface Input {

    /**
     * Method that defines input method.
     *
     * @return                              String
     */
    String in();

    /**
     * Method that defines input method with String[] parameter.
     *
     * @return                              String
     * @param strings                       an array of strings to be used for input
     * @param index                         index of a string to be returned
     */
    String in(String[] strings, int index);
}

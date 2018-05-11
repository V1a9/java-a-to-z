package com.vgoryashko.sortfile;

import java.util.Comparator;

/**
 * Class that compare two strings by length.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 05.02.2017
 */
public class StringLengthSort implements Comparator<String> {

    /**
     * Method that overrides a compare method for Strings.
     * @param string1               First string for comparison
     * @param string2               Second string for comparison
     * @return int value            1 if >, -1 if <, 0 if ==
     */
    @Override
    public int compare(String string1, String string2) {
        if (string1.length() > string2.length()) {
            return 1;
        } else {
            if (string1.length() < string2.length()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

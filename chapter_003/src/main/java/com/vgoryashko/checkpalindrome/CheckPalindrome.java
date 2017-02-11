package com.vgoryashko.checkpalindrome;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that performs check if a word is a palindrome.
 *
 * @author Vlad Goryashko
 * @version 0.8
 * @since 11.02.2017
 */
public class CheckPalindrome {

    /**
     * Variables that references to an object of the type Pattern.
     */
    private Pattern pattern;

    /**
     * Constructor for the class.
     */
    public CheckPalindrome() {
        this.pattern = Pattern.compile("([a-z])([a-z])[a-z]\\2\\1", Pattern.CASE_INSENSITIVE);
    }

    /**
     * Method getter for the member pattern.
     * @return                          Pattern
     */
    public Pattern getPattern() {
        return this.pattern;
    }

    /**
     * Method that checks if a word is a palindrome.
     *
     * @param chars                         array of symbols to be checked
     * @return boolean
     */
    public boolean checkPalindrome(char[] chars) {
        boolean result = false;
        String string = String.copyValueOf(chars);
        Matcher matcher = pattern.matcher(string);
        if (matcher.matches()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
}

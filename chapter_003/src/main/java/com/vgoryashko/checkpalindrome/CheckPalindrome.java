package com.vgoryashko.checkpalindrome;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that performs check if a word is a palindrome.
 *
 * @author Vlad Goryashko
 * @version 0.6
 * @since 08.02.2017
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
     * @param aWord                         a word to be checked
     * @return boolean
     */
    public boolean checkPalindrome(String aWord) {
        boolean result = false;
        Matcher matcher = getPattern().matcher(aWord);
        if (aWord.length() != 5) {
            System.out.printf("This word \"%s\" is not 5 letters long, please try again.".concat(System.getProperty("line.separator")), aWord);
        } else if (matcher.matches()) {
            System.out.printf("This word \"%s\" is a palindrome.".concat(System.getProperty("line.separator")), aWord);
            result = true;
        } else {
            System.out.printf("This word \"%s\" is not a palindrome.".concat(System.getProperty("line.separator")), aWord);
            result = false;
        }
        return result;
    }
}

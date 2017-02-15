package com.vgoryashko.checkpalindromev2;

/**
 * Class that performs check if a word is a palindrome.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 15.02.2017
 */
public class CheckPalindrome {

    /**
     * Variable that stores a string to be checked.
     */
    private String word;

    /**
     * Constructor for the class.
     *
     * @param aWord                     a word to be checked
     */
    public CheckPalindrome(String aWord) {
        this.word = aWord;
    }

    /**
     * Method getter to retrieve word.
     *
     * @return                          word
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Method that checks if a word is a palindrome.
     *
     * @return boolean
     * @throws RuntimeException                 RuntimeException
     */
    public boolean checkPalindrome() throws RuntimeException {
        boolean result = false;
        StringBuilder tempString = new StringBuilder(getWord().toLowerCase());
        if (tempString.length() != 5) {
            throw new InvalidNumberOfLettersException("Word length isn't 5 letters long, please enter a new word.");
        } else {
            String temp = tempString.reverse().toString();
            if (temp.equals(getWord().toLowerCase())) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }
}
